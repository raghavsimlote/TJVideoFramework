package com.traveljar.vo;

import org.json.me.JSONObject;

public class MoodVO {
	
	public MoodVO(JSONObject moodJson) {
		if ( moodJson != null ) {
			this.setPerson(moodJson.optString("person"));
			this.setMood(moodJson.optString("mood"));
			this.setReason(moodJson.optString("reason"));
		}
	}
	
	private String person;
	private String mood;
	private String reason;
	
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
}
