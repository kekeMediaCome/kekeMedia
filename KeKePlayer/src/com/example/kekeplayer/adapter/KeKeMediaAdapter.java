package com.example.kekeplayer.adapter;

import java.util.List;

import com.example.kekeplayer.R;
import com.example.kekeplayer.type.ProjectType;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class KeKeMediaAdapter extends BaseAdapter{

	private LayoutInflater mLayoutInflater;
	public List<ProjectType> listItem;
	public KeKeMediaAdapter(Context context){
		mLayoutInflater  = (LayoutInflater)context.getSystemService("layout_inflater"); 
	}
	public void setListProject(List<ProjectType> list){
		if (list != null && list.size() > 0) {
			listItem = list;
			notifyDataSetChanged();
		}
	}
	@Override
	public int getCount() {
		if (listItem != null) {
			return listItem.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return listItem.get(position);
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
			convertView = mLayoutInflater.inflate(R.layout.kekemediaitem, null);
			viewHolder.name = (TextView)convertView.findViewById(R.id.kekemediaitem_title);
			viewHolder.logo = (ImageView)convertView.findViewById(R.id.kekemediaitem_logo);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		switch (position) {
		case 0:
			viewHolder.logo.setBackgroundResource(R.drawable.favor_app);
			break;
		case 1:
			viewHolder.logo.setBackgroundResource(R.drawable.cticon_13);
			break;
		}
		ProjectType projectType = listItem.get(position);
		viewHolder.name.setText(projectType.getName());
		return convertView;
	}

	class ViewHolder{
		ImageView logo;
		TextView name;
	}
}
