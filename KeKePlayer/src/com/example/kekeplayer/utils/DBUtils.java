package com.example.kekeplayer.utils;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.kekeplayer.db.DBHelper;
import com.example.kekeplayer.type.History;
import com.example.kekeplayer.type.Remind;

public class DBUtils {
	private static DBHelper mDBHelper;
	private String TAG;
	private Context mContext;
	
	public DBUtils(Context paramContext){
		TAG = super.getClass().getSimpleName();
		mContext = paramContext;
		mDBHelper = new DBHelper(paramContext);
	}
	private void insertHistory(History history){
		
	}
	
	
	public void close(){
		if (mDBHelper != null) {
			mDBHelper.close();
		}
	}
	
	public void deleteAllCollects(){
		SQLiteDatabase localSQLiteDatabase = mDBHelper.getWritableDatabase();
		localSQLiteDatabase.delete("collect", "", null);
		localSQLiteDatabase.close();
		close();
	}
	
	public void deleteAllHistory(){
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		db.delete("history", "", null);
		db.close();
		close();
	}
	
	public void deleteAllRemind(){
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		db.delete("remind", "", null);
		db.close();
		close();
	}
	
	public void deleteOneCollect(int paramInt){
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		try {
			String sql = "delete from collect where _id = "+paramInt;
			db.execSQL(sql);
		} catch (Exception e) {
		}finally{
			db.close();
		}
	}
	
	public void deleteOneHistory(int paramInt){
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		try {
			String sql = "delete from history where _id = "+paramInt;
			db.execSQL(sql);
		} catch (Exception e) {
		}finally{
			db.close();
		}
	}
	
	public void deleteOneRemind(String paramInt){
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		try {
			String sql = "delete from remind where _id = "+paramInt;
			db.execSQL(sql);
		} catch (Exception e) {
		}finally{
			db.close();
		}
	}
	
	public void deleteSomeCollects(List<Integer> paramList){
//		if (paramList == null || paramList.size() == 0) {
//			return;
//		}
//		SQLiteDatabase db = mDBHelper.getWritableDatabase();
//		try {
//			
//		} catch (Exception e) {
//		}
	}
	
	public int getAllRemindsCount(){
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT count(*) FROM remind", null);
		cursor.moveToNext();
		int i = cursor.getInt(0);
		cursor.close();
		db.close();
		return i;
	}
	
	public void insertRemind(Remind paramRemind){
		SQLiteDatabase db = null;
		try {
			if (!StringUtils.isBlank(paramRemind.getTitle())) {
				db = mDBHelper.getWritableDatabase();
				ContentValues contentValues = new ContentValues();
				contentValues.put("title", paramRemind.getTitle());
				contentValues.put("channel_name", paramRemind.getChannel_name());
				contentValues.put("remind_time", paramRemind.getRemind_time());
				contentValues.put("is_new", Integer.valueOf(paramRemind.getIs_new()));
				contentValues.put("pic", paramRemind.getPic());
				db.insertOrThrow("remind", null, contentValues);
			}
		} catch (Exception e) {
		}finally{
			db.close();
		}
	}
	
	public int getRemindStatus(String title, String remind_time){
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		String[] arrayOfString = new String[2];
		arrayOfString[0] = title;
		arrayOfString[1] = remind_time;
		Cursor cursor = db.rawQuery("SELECT count(*) FROM remind where title=? and remind_time=?", arrayOfString);
		cursor.moveToNext();
		int i = cursor.getInt(0);
		cursor.close();
		db.close();
		return i;
	}
}
















