package com.traveljar.vo;

import java.util.Vector;

import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

public class JourneyVO {
	
	public JourneyVO(JSONObject journeyJson) {
		if(journeyJson!=null){
			this.setName(journeyJson.optString("name"));
			this.setTagLine(journeyJson.optString("tag_line"));
			this.setStartDate(journeyJson.optString("start_date"));
			this.setEndDate(journeyJson.optString("end_date"));
			this.setNumberOfDays(journeyJson.optString("no_of_days"));
			this.setBgImagePath(journeyJson.optString("bg_image"));
			JSONObject placeJson = journeyJson.optJSONObject("place");
			if ( placeJson != null ) {
				try {
					JSONArray placesArray = placeJson.getJSONArray("place_item");
					if ( placesArray != null && placesArray.length()>0 ) {
						Vector<String> placesVector = new Vector<String>();
						for (int i=0; i<placesArray.length(); i++) {
							String placeName = (String) placesArray.get(i);
							placesVector.add(placeName);
						}
						this.setPlacesList(placesVector);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
	private String name;
	private String tagLine;
	private String startDate;
	private String endDate;
	private String numberOfDays;
	private String bgImagePath;
	private Vector<String> placesList;
	
	
	
	public String getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(String numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
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
	
	public String getBgImagePath() {
		return bgImagePath;
	}
	public void setBgImagePath(String bgImagePath) {
		this.bgImagePath = bgImagePath;
	}
	public Vector<String> getPlacesList() {
		return placesList;
	}
	public void setPlacesList(Vector<String> placesList) {
		this.placesList = placesList;
	}
		
}