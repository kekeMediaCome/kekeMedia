package com.example.kekeplayer.activity;

import java.util.ArrayList;

import com.example.kekeplayer.dao.TogicTvChannelDAO;
import com.example.kekeplayer.player.Togic_Live_Select_VideoPlayer;
import com.example.kekeplayer.type.TogicTvChannelType;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class KeKeLive_Togic extends Activity {
	public ProgressDialog progressDialog;
	public static ArrayList<TogicTvChannelType> listItems = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		progressDialog = new ProgressDialog(KeKeLive_Togic.this);
		progressDialog.setMessage("加载中...");
		InitData localInitData = new InitData();
		Void[] arrayOfVoid = new Void[0];
		localInitData.execute(arrayOfVoid);
	}

	class InitData extends AsyncTask<Void, Void, Void> {
		InitData() {
		}

		@Override
		protected Void doInBackground(Void... paramArrayOfVoid) {
			TogicTvChannelDAO dao = new TogicTvChannelDAO();
			try {
				if (listItems == null) {
					listItems = dao.getTvChannel();
				}
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
			if (listItems != null && listItems.size() > 0) {
				Intent intent = new Intent();
				intent.putExtra("listchannel", listItems);
				intent.setClass(KeKeLive_Togic.this,
						Togic_Live_Select_VideoPlayer.class);
				startActivity(intent);
				finish();
			}
		}
	}
}