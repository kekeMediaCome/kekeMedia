package com.example.kekeplayer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.kekeplayer.dom.CutvDom;
import com.example.kekeplayer.imageloader.AbsListViewBaseActivity;
import com.example.kekeplayer.type.CutvLive;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class KeKeLive extends AbsListViewBaseActivity implements
		OnItemClickListener {
	DisplayImageOptions options;
	public List<CutvLive> listItems = null;
	private ItemAdapter itemAdapter;
	public final static String cutvurl = "http://ugc.sun-cam.com/api/tv_live_api.php?action=tv_live&prod_type=android";
	public final static String cutv_sub_url = "http://ugc.sun-cam.com/api/tv_live_api.php?action=channel_prg_list&tv_id=";
	public ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kekelive);

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
		progressDialog = new ProgressDialog(KeKeLive.this);
		progressDialog.setMessage("加载中...");
		InitData localInitData = new InitData();
		Void[] arrayOfVoid = new Void[0];
		localInitData.execute(arrayOfVoid);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	class InitData extends AsyncTask<Void, Void, Void> {
		InitData() {
		}
		@Override
		protected Void doInBackground(Void... paramArrayOfVoid) {
			try {
				listItems = CutvDom.parseXml(cutvurl);
			} catch (Exception e) {
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

	class ItemAdapter extends BaseAdapter {

		private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

		private class ViewHolder {
			public TextView text;
			public ImageView image;
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
				view = getLayoutInflater().inflate(R.layout.kekelive_item,
						parent, false);
				holder = new ViewHolder();
				holder.text = (TextView) view.findViewById(R.id.text);
				holder.image = (ImageView) view.findViewById(R.id.image);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			CutvLive cutvLive = listItems.get(position);
//			Log.e("dd", cutvLive.getTv_name());
			holder.text.setText(cutvLive.getTv_name());
			imageLoader.displayImage(cutvLive.getTv_thumb_img(), holder.image,
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
		CutvLive cutvLive = listItems.get(position);
		Bundle bundle = new Bundle();
		bundle.putSerializable("cutvlive", cutvLive);
		Intent intent = new Intent();
		intent.putExtras(bundle);
		intent.setClass(KeKeLive.this, KeKeLiveSub.class);
		startActivity(intent);
	}
}
