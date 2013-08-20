package com.example.kekeplayer.activity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.example.kekeplayer.R;
import com.example.kekeplayer.dao.TogicTvChannelDAO;
import com.example.kekeplayer.imageloader.AbsListViewBaseActivity;
import com.example.kekeplayer.player.JieLiveVideoPlayer;
import com.example.kekeplayer.type.TogicTvChannelType;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class KeKeLive_Togic extends AbsListViewBaseActivity implements OnItemClickListener{
	public ProgressDialog progressDialog;
	public List<TogicTvChannelType> listItems = null;
	DisplayImageOptions options;
	private ItemAdapter itemAdapter;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.togiclive);
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(20))
				.build();
		progressDialog = new ProgressDialog(KeKeLive_Togic.this);
		progressDialog.setMessage("加载中...");
		listView = (ListView) findViewById(android.R.id.list);
		itemAdapter = new ItemAdapter();
		((ListView) listView).setAdapter(itemAdapter);
		listView.setOnItemClickListener(this);
		InitData localInitData = new InitData();
		Void[] arrayOfVoid = new Void[0];
		localInitData.execute(arrayOfVoid);
	}

	class ItemAdapter extends BaseAdapter {

		private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

		private class ViewHolder {
			public TextView text;
			public ImageView image;
			public TextView num;
		}

		@Override
		public int getCount() {
			if (listItems != null) {
				return listItems.size();
			} else {
				return 0;
			}
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View view,
				ViewGroup parent) {
			final ViewHolder holder;
			if (view == null) {
				view = getLayoutInflater().inflate(R.layout.togiclive_item,
						parent, false);
				holder = new ViewHolder();
				holder.text = (TextView) view.findViewById(R.id.togic_text);
				holder.image = (ImageView) view.findViewById(R.id.togic_image);
				holder.num = (TextView)view.findViewById(R.id.togic_num);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			TogicTvChannelType togicTvChannelType = listItems.get(position);
			holder.text.setText(togicTvChannelType.getTitle());
			holder.num.setText(togicTvChannelType.getNum()+"");
			imageLoader.displayImage(togicTvChannelType.getIcon(), holder.image,
					options, animateFirstListener);
			return view;
		}
	}

	private static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

	class InitData extends AsyncTask<Void, Void, Void> {
		InitData() {
		}

		@Override
		protected Void doInBackground(Void... paramArrayOfVoid) {
			TogicTvChannelDAO dao = new TogicTvChannelDAO();
			try {
				listItems = dao.getTvChannel();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog.show();
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			itemAdapter.notifyDataSetChanged();
			progressDialog.cancel();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		TogicTvChannelType type = listItems.get(position);
		Intent intent = new Intent();
		intent.putExtra("path", type.getUrls().get(0));
		intent.putExtra("title", type.getTitle());
		intent.setClass(KeKeLive_Togic.this, JieLiveVideoPlayer.class);
		startActivity(intent);
	}
}
