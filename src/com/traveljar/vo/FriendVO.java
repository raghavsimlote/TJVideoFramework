package com.traveljar.vo;

import org.json.me.JSONObject;

public class FriendVO {
	
	public FriendVO(JSONObject friendJson) {
		if ( friendJson != null ) {
			this.setName(friendJson.optString("name"));
			this.setProfileImagePath(friendJson.optString("profile_img"));
		}
	}
	
	private String name;
	private String profileImagePath;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfileImagePath() {
		return profileImagePath;
	}
	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}
	
}
