package com.traveljar.vo;

import java.util.List;

import org.json.me.JSONObject;

public class JourneyVO {
	
	public JourneyVO(JSONObject journeyJson) {
		if(journeyJson!=null){
			this.setName(journeyJson.optString("name"));
			this.setTagLine(journeyJson.optString("tag_line"));
			this.setStartDate(journeyJson.optString("start_date"));
			this.setEndDate(journeyJson.optString("end_date"));
			this.setBrImagePath(journeyJson.optString("bg_image"));
		}
	}
	
	private String name;
	private String tagLine;
	private String startDate;
	private String endDate;
	private String brImagePath;
	private List <String> placesList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTagLine() {
		return tagLine;
	}
	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getBrImagePath() {
		return brImagePath;
	}
	public void setBrImagePath(String brImagePath) {
		this.brImagePath = brImagePath;
	}
	public List<String> getPlacesList() {
		return placesList;
	}
	public void setPlacesList(List<String> placesList) {
		this.placesList = placesList;
	}
	
	
	
	
}
