package com.example.kekeplayer.activity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.kekeplayer.R;
import com.example.kekeplayer.adapter.CustomExpandableAdapter;
import com.example.kekeplayer.player.JieLiveVideoPlayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

public class CustomExpand extends Activity implements OnChildClickListener{

	private List<String> groupArray;
	private List<List<String[]>> childArray;
	CustomExpandableAdapter adapter;
	
	ExpandableListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customexpandablelistview);
		groupArray = new ArrayList<String>();
		childArray = new ArrayList<List<String[]>>();
		ReadSelfChannel("kekePlayer/tvlist.txt");
		listView = (ExpandableListView) findViewById(R.id.expandableListView);
		adapter = new CustomExpandableAdapter(this, groupArray, childArray);
		listView.setAdapter(adapter);
		listView.setOnChildClickListener(this);
		
	}
	
	public void ReadSelfChannel(String filename){
		String path = null;
		String code = "GBK";
		File file = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
		if (sdCardExist) {
			path = Environment.getExternalStorageDirectory()+"/"+filename;
			file = new File(path);
			if (!file.exists()) {
				Toast.makeText(this, "请检查文件是否存在", Toast.LENGTH_LONG).show();
				finish();
				return;
			}
		}else {
			Toast.makeText(this, "请检查SD卡是否存在", Toast.LENGTH_LONG).show();
			finish();
			return;
		}
		try {
			// 探测txt文件的编码格式
			code = codeString(path);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			InputStream inStream = new FileInputStream(file);
			if (inStream != null) {
				InputStreamReader inputreader = new InputStreamReader(inStream, code);
				 BufferedReader buffreader = new BufferedReader(inputreader);
				 String line;
				 String[] splitArray;
				 int lastGroup = 0;
				 while (( line = buffreader.readLine()) != null) {
					 splitArray = line.split(",");
					 if (splitArray != null && splitArray.length == 3) {
						 lastGroup = groupArray.size() - 1;
						 if (lastGroup == -1) {
							 lastGroup = 0;
							 groupArray.add(splitArray[0]);
						}else if (groupArray.get(lastGroup).equals(splitArray[0])) {
							//首先检测最后一个类型
						}else {
							lastGroup = CheckGroupName(splitArray[0]);
							if (lastGroup == -1) {
								groupArray.add(splitArray[0]);
								lastGroup = groupArray.size() - 1;
							}
						}
						 String[] tempArray = new String[2];
						 tempArray[0] = splitArray[1];
						 tempArray[1] = splitArray[2];
						 List<String[]> temlist;
						 int size = childArray.size();
						 if (size == 0 || (size -1) < lastGroup) {
							 temlist = new ArrayList<String[]>();
						}else {
							 temlist = childArray.get(lastGroup);
							 childArray.remove(lastGroup);
						}
						 temlist.add(tempArray);
						 childArray.add(lastGroup, temlist);
					}	
				}
				 buffreader.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int CheckGroupName(String name){
		int count = groupArray.size();
		for (int i = 0; i < count; i++) {
			if (name.equals(groupArray.get(i))) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * 判断文件的编码格式
	 * 
	 * @param fileName
	 *            :file
	 * @return 文件编码格式
	 * @throws Exception
	 */
	private static String codeString(String fileName) throws Exception {
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(
				fileName));
		int p = (bin.read() << 8) + bin.read();
		String code = null;

		switch (p) {
		case 0xefbb:
			code = "UTF-8";
			break;
		case 0xfffe:
			code = "Unicode";
			break;
		case 0xfeff:
			code = "UTF-16BE";
			break;
		default:
			code = "GBK";
		}

		bin.close();

		Log.d("Parseutil", "find text code ===>" + code);

		return code;
	}
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		String[] channel = childArray.get(groupPosition).get(childPosition);
		Intent intent = new Intent();
		intent.setClass(CustomExpand.this, JieLiveVideoPlayer.class);
		intent.putExtra("title", channel[0]);
		intent.putExtra("path", channel[1]);
		startActivity(intent);
		return true;
	}
}