package com.example.kekeplayer.activity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.example.kekeplayer.R;
import com.example.kekeplayer.dao.TogicTvChannel2DAO;
import com.example.kekeplayer.imageloader.AbsListViewBaseActivity;
import com.example.kekeplayer.player.JieLiveVideoPlayer;
import com.example.kekeplayer.type.TogicTvChannel2Type;
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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class KeKeLive_Togic2 extends AbsListViewBaseActivity implements
		OnItemClickListener {
	public ProgressDialog progressDialog;
	public List<TogicTvChannel2Type> listItems = null;
	DisplayImageOptions options;
	private ItemAdapter itemAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.togiclive2);
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(20))
				.build();
		progressDialog = new ProgressDialog(KeKeLive_Togic2.this);
		progressDialog.setMessage("加载中...");
		listView = (ListView) findViewById(android.R.id.list);
		itemAdapter = new ItemAdapter();
		((ListView) listView).setAdapter(itemAdapter);
		listView.setOnItemClickListener(this);
		InitData localInitData = new InitData();
		Void[] arrayOfVoid = new Void[0];
		localInitData.execute(arrayOfVoid);
	}

	class InitData extends AsyncTask<Void, Void, Void> {
		InitData() {
		}

		@Override
		protected Void doInBackground(Void... paramArrayOfVoid) {
			TogicTvChannel2DAO dao = new TogicTvChannel2DAO();
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
			progressDialog.cancel();
			itemAdapter.notifyDataSetChanged();
		}
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
		public View getView(final int position, View view, ViewGroup parent) {
			final ViewHolder holder;
			if (view == null) {
				view = getLayoutInflater().inflate(R.layout.togiclive_item,
						parent, false);
				holder = new ViewHolder();
				holder.text = (TextView) view.findViewById(R.id.togic_text);
				holder.image = (ImageView) view.findViewById(R.id.togic_image);
				holder.num = (TextView) view.findViewById(R.id.togic_num);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			TogicTvChannel2Type type = listItems.get(position);
			holder.text.setText(type.getChannel_name());
			holder.num.setText(type.getChannel_id() + "");
			imageLoader.displayImage(type.getIcon_url(), holder.image, options,
					animateFirstListener);
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		TogicTvChannel2Type type = listItems.get(position);
		Intent intent = new Intent();
		intent.putExtra("path", type.getUrl());
		intent.putExtra("title", type.getChannel_name());
		intent.setClass(KeKeLive_Togic2.this, JieLiveVideoPlayer.class);
		startActivity(intent);
	}
}
