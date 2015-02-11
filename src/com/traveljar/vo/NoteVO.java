package com.traveljar.vo;

import org.json.me.JSONObject;

public class NoteVO {
	
	public NoteVO(JSONObject noteJson) {
		if ( noteJson != null ) {
			this.setPath(noteJson.optString("path"));
			this.setCaption(noteJson.optString("caption"));
			this.setDescription(noteJson.optString("description"));
			this.setPlace(noteJson.optString("place"));
			this.setDate(noteJson.optString("date"));
		}
	}

	private String path;
	private String caption;
	private String description;
	private String place;
	private String date;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
