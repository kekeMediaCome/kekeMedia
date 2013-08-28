package com.example.kekeplayer.activity;

import com.example.kekeplayer.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class KeKeMovie extends Activity{

	private PullToRefreshGridView movieRefresh;
	private GridView movieGridView;
	DisplayImageOptions options;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kekemovie);
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
		.cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(20))
		.build();
		movieRefresh = (PullToRefreshGridView)findViewById(R.id.pull_refresh_grid);
		movieRefresh.setMode(Mode.PULL_FROM_END);
		movieGridView = movieRefresh.getRefreshableView();
		movieRefresh.setOnRefreshListener(new OnRefreshListener<GridView>() {
			@Override
			public void onRefresh(PullToRefreshBase<GridView> refreshView) {
				
			}
		});
	}

}
