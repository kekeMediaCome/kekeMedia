package com.example.kekeplayer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kekeplayer.dom.CutvSubDom;
import com.example.kekeplayer.imageloader.AbsListViewBaseActivity;
import com.example.kekeplayer.type.CutvLive;
import com.example.kekeplayer.type.CutvLiveSub;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class KeKeLiveSub extends AbsListViewBaseActivity implements
		OnItemClickListener {

	DisplayImageOptions options;
	public List<CutvLiveSub> listItems = null;
	private ItemAdapter itemAdapter;
	public final static String cutvurl = "http://ugc.sun-cam.com/api/tv_live_api.php?action=tv_live&prod_type=android";
	public String cutv_sub_url = "http://ugc.sun-cam.com/api/tv_live_api.php?action=channel_prg_list&tv_id=";
	public CutvLive cutvLive;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kekelive);
		Bundle bundle = getIntent().getExtras();
		cutvLive = (CutvLive) bundle.getSerializable("cutvlive");
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(20))
				.build();
		
		listView = (ListView) findViewById(android.R.id.list);
		itemAdapter = new ItemAdapter();
		((ListView) listView).setAdapter(itemAdapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		cutv_sub_url = cutv_sub_url + cutvLive.getTv_id();
		listItems = CutvSubDom.parseXml(cutv_sub_url);
		itemAdapter.notifyDataSetChanged();
	}

	class ItemAdapter extends BaseAdapter {

		private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

		private class ViewHolder {
			public TextView sub_text;
			public ImageView sub_image;
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
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			View view = convertView;
			final ViewHolder holder;
			if (convertView == null) {
				view = getLayoutInflater().inflate(R.layout.kekelivesub_item,
						parent, false);
				holder = new ViewHolder();
				holder.sub_text = (TextView) view.findViewById(R.id.sub_text);
				holder.sub_image = (ImageView) view.findViewById(R.id.sub_image);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			CutvLiveSub cutvLive = listItems.get(position);
			holder.sub_text.setText(cutvLive.getChannel_name());
			imageLoader.displayImage(cutvLive.getThumb(), holder.sub_image,
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		CutvLiveSub cutvLiveSub = listItems.get(position);
		Intent intent = new Intent();
		intent.putExtra("path", cutvLiveSub.getMobile_url());
		intent.putExtra("title", cutvLiveSub.getChannel_name());
		intent.setClass(KeKeLiveSub.this, JieLiveVideoPlayer.class);
		startActivity(intent);
		finish();
	}

}
