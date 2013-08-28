package com.example.kekeplayer.activity;


import java.util.ArrayList;
import java.util.List;

import com.example.kekeplayer.R;
import com.example.kekeplayer.adapter.KeKeMovie_Adapter;
import com.example.kekeplayer.imageloader.AbsListViewBaseActivity;
import com.example.kekeplayer.parse.jsoup.ParseYouKu;
import com.example.kekeplayer.player.JieLiveVideoPlayer;
import com.example.kekeplayer.type.MovieType;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.yixia.vparser.VParser;
import com.yixia.vparser.model.Video;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class KeKeMovie extends AbsListViewBaseActivity{

	private PullToRefreshGridView movieRefresh;
	public KeKeMovie instance;
//	private AbsListView movieGridView;
	DisplayImageOptions options;
	public KeKeMovie_Adapter adapter;
	VParser vParser;
	public List<MovieType> list_main = new ArrayList<MovieType>();
	String[] links = {  "http://movie.youku.com/search/index2/_page63561_1_cmodid_63561",
						"http://movie.youku.com/search/index2/_page63561_2_cmodid_63561",
						"http://movie.youku.com/search/index2/_page63561_3_cmodid_63561",
						"http://movie.youku.com/search/index2/_page63561_4_cmodid_63561",
						"http://movie.youku.com/search/index2/_page63561_5_cmodid_63561",
						"http://movie.youku.com/search/index2/_page63561_6_cmodid_63561",
						"http://movie.youku.com/search/index2/_page63561_7_cmodid_63561",
						"http://movie.youku.com/search/index2/_page63561_8_cmodid_63561"};
	int index = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kekemovie);
		instance = this;
		movieRefresh = (PullToRefreshGridView)findViewById(R.id.pull_refresh_grid);
		movieRefresh.setMode(Mode.PULL_FROM_END);
		listView = movieRefresh.getRefreshableView();
		movieRefresh.setOnRefreshListener(new OnRefreshListener<GridView>() {
			@Override
			public void onRefresh(PullToRefreshBase<GridView> refreshView) {
				if (index < 8) {
					index++;
					new InitData().execute();
				}else {
					movieRefresh.onRefreshComplete();
					Toast.makeText(instance, "已经全部加在结束", Toast.LENGTH_SHORT).show();
				}
			}
		});
		adapter = new KeKeMovie_Adapter(this, imageLoader);
		((GridView) listView).setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Video video  = vParser.parse(list_main.get(position).getMovie_url());
				if (video != null && video.videoUri.endsWith("m3u8")) {
					Intent intent = new Intent();
					intent.putExtra("path", video.videoUri);
					intent.putExtra("title", list_main.get(position).getMovie_name());
					intent.setClass(KeKeMovie.this,JieLiveVideoPlayer.class);
					startActivity(intent);
				}
			}
		});
		new InitData().execute();
		vParser = new VParser(this);
	}
	class InitData extends AsyncTask<Void, Void, Void> {
		InitData() {
		}
		List<MovieType> lists = null;
		@Override
		protected Void doInBackground(Void... paramArrayOfVoid) {
			
			try {
				ParseYouKu parseYouKu = new ParseYouKu();
				lists = parseYouKu.ParserYouKu(links[index]);
				for (MovieType list : lists) {
					list_main.add(list);
				}
			} catch (Exception e) {
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			adapter.setListItem(list_main);
			movieRefresh.onRefreshComplete();
		}
	}

}
