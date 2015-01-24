package com.traveljar.vo;

import java.util.List;

public class StoneVO {
	
	private String name;
	private String type;
	private List <PictureVO> pictures;
	private List <VideoVO> videos;
	private List <MoodVO> moods;
	
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
	public List<PictureVO> getPictures() {
		return pictures;
	}
	public void setPictures(List<PictureVO> pictures) {
		this.pictures = pictures;
	}
	public List<VideoVO> getVideos() {
		return videos;
	}
	public void setVideos(List<VideoVO> videos) {
		this.videos = videos;
	}
	public List<MoodVO> getMoods() {
		return moods;
	}
	public void setMoods(List<MoodVO> moods) {
		this.moods = moods;
	}
	
	
	
	
}
