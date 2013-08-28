package com.example.kekeplayer.adapter;

import java.util.List;

import com.example.kekeplayer.R;
import com.example.kekeplayer.type.MovieType;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class KeKeMovie_Adapter extends BaseAdapter{

	private List<MovieType> listItems;
	private LayoutInflater inflater;
	public KeKeMovie_Adapter(Context context){
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		if (listItems != null) {
			return listItems.size();
		}else {
			return 0;
		}
		
	}

	@Override
	public Object getItem(int position) {
		return listItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.kekemovie_item, null);
			viewHolder.movie_img = (ImageView)convertView.findViewById(R.id.movie_img);
			viewHolder.movie_type = (TextView)convertView.findViewById(R.id.movie_type);
			viewHolder.movie_time = (TextView)convertView.findViewById(R.id.movie_time);
			viewHolder.movie_name = (TextView)convertView.findViewById(R.id.movie_name);
			viewHolder.movie_langure = (TextView)convertView.findViewById(R.id.movie_langure);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		
		MovieType movieType = listItems.get(position);
		
		return convertView;
	}
	
	class ViewHolder{
		private ImageView movie_img;
		private TextView movie_type;
		private TextView movie_time;
		private TextView movie_name;
		private TextView movie_langure;
	}

}
