package com.traveljar.vo;

import java.util.Vector;

import org.json.me.JSONObject;

public class StoneVO {
	
	public StoneVO(JSONObject stonesJson) {
		
	}
	
	private String name;
	private String type;
	private Vector <PictureVO> pictures;
	private Vector <VideoVO> videos;
	private Vector <MoodVO> moods;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Vector<PictureVO> getPictures() {
		return pictures;
	}
	public void setPictures(Vector<PictureVO> pictures) {
		this.pictures = pictures;
	}
	public Vector<VideoVO> getVideos() {
		return videos;
	}
	public void setVideos(Vector<VideoVO> videos) {
		this.videos = videos;
	}
	public Vector<MoodVO> getMoods() {
		return moods;
	}
	public void setMoods(Vector<MoodVO> moods) {
		this.moods = moods;
	}
	
	
	
	
}
