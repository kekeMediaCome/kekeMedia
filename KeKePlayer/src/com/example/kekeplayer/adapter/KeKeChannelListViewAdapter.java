package com.example.kekeplayer.adapter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.example.kekeplayer.R;
import com.example.kekeplayer.type.TvChannel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class KeKeChannelListViewAdapter extends BaseAdapter{
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	private LayoutInflater inflater;
	private List<TvChannel> mTvChannelList;
	DisplayImageOptions options;
	ImageLoader imageLoader;
	
	public KeKeChannelListViewAdapter(Context mContext, ImageLoader imageLoader) {
		super();
		inflater = LayoutInflater.from(mContext);
		this.imageLoader = imageLoader;
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.displayer(new RoundedBitmapDisplayer(20))
		.build();
	}

	@Override
	public int getCount() {
		if (mTvChannelList != null) {
			return mTvChannelList.size();
		}else {
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		return mTvChannelList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final TvChannel localTvChannel = (TvChannel)mTvChannelList.get(position);
		HolderView holderView;
		if (convertView == null) {
			holderView = new HolderView();
			convertView = inflater.inflate(R.layout.tv_list_item, null);
			holderView.logo = (ImageView)convertView.findViewById(R.id.logo);
			holderView.tv_title = (TextView)convertView.findViewById(R.id.tv_title);
			holderView.content = (TextView)convertView.findViewById(R.id.content);
			holderView.playBtn = (ImageView)convertView.findViewById(R.id.play_btn);
			convertView.setTag(holderView);
		}else {
			holderView = (HolderView)convertView.getTag();
		}
		holderView.tv_title.setText(localTvChannel.getChannelname());
		holderView.content.setText(localTvChannel.getProgram_name());
		imageLoader.displayImage(localTvChannel.getChannellogo(), holderView.logo, options, animateFirstListener);
		return convertView;
	}
	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
		static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
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
	class HolderView {
		ImageView logo;
		TextView tv_title;
		TextView content;
		ImageView playBtn;
	}
	
	public List<TvChannel> getmTvChanenList()
	{
		return mTvChannelList;
	}
	public void notifyDataSetChanged(List<TvChannel> paramList)
	  {
	    if (paramList == null)
	      return;
	    this.mTvChannelList = paramList;
	    notifyDataSetChanged();
	  }
	
}