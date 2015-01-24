package com.traveljar.vo;

import java.util.Vector;

public class TravelJarVO {
	
	private JourneyVO journey;
	private Vector<FriendVO> friendsVector;
	private Vector<StoneVO> milestonesVector;
	
	public JourneyVO getJourney() {
		return journey;
	}
	public void setJourney(JourneyVO journey) {
		this.journey = journey;
	}
	public Vector<FriendVO> getFriendsVector() {
		return friendsVector;
	}
	public void setFriendsVector(Vector<FriendVO> friendsVector) {
		this.friendsVector = friendsVector;
	}
	public Vector<StoneVO> getMilestonesVector() {
		return milestonesVector;
	}
	public void setMilestonesVector(Vector<StoneVO> milestonesVector) {
		this.milestonesVector = milestonesVector;
	}
	
	
	
}
