package com.example.kekeplayer.type;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class MovieType implements Parcelable{

	private String movie_img;
	private String movie_type;
	private String movie_time;
	private String movie_name;
	private String movie_langure;
	
	public MovieType(){
		
	}
	
	public MovieType(Parcel in){
		readFromParcel(in);
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(movie_img);
		dest.writeString(movie_type);
		dest.writeString(movie_time);
		dest.writeString(movie_name);
		dest.writeString(movie_langure);
	}
	
	public void readFromParcel(Parcel in){
		movie_img = in.readString();
		movie_type = in.readString();
		movie_time = in.readString();
		movie_name = in.readString();
		movie_langure = in.readString();
	}
	
	public static final Parcelable.Creator<MovieType> CREATOR = new Parcelable.Creator<MovieType>() {

		@Override
		public MovieType createFromParcel(Parcel in) {
			return new MovieType(in);
		}

		@Override
		public MovieType[] newArray(int size) {
			return new MovieType[size];
		} 
		
	};

	public String getMovie_img() {
		return movie_img;
	}

	public void setMovie_img(String movie_img) {
		this.movie_img = movie_img;
	}

	public String getMovie_type() {
		return movie_type;
	}

	public void setMovie_type(String movie_type) {
		this.movie_type = movie_type;
	}

	public String getMovie_time() {
		return movie_time;
	}

	public void setMovie_time(String movie_time) {
		this.movie_time = movie_time;
	}

	public String getMovie_name() {
		return movie_name;
	}

	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}

	public String getMovie_langure() {
		return movie_langure;
	}

	public void setMovie_langure(String movie_langure) {
		this.movie_langure = movie_langure;
	}

	
}
