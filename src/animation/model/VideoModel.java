package animation.model;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.json.me.JSONArray;
import org.json.me.JSONObject;

import animation.utility.LogUtility;

import com.traveljar.vo.FriendVO;
import com.traveljar.vo.JourneyVO;
import com.traveljar.vo.StoneVO;
import com.traveljar.vo.TravelJarVO;


public class VideoModel {
	
	public TravelJarVO createVideo() {
		
		TravelJarVO travelJarVO = null;
		
		try {
		
			File file = new File("E://Log/traveljar_json.txt");
			
			org.json.simple.parser.JSONParser jsonParser = new org.json.simple.parser.JSONParser();
			org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) jsonParser.parse(new FileReader(file));
			String jsonString = jsonObject.toString();
			
			travelJarVO = new TravelJarVO();
			
			JSONObject mainJson = new JSONObject(jsonString);
			JSONObject journeyJson =  mainJson.getJSONObject("journey");
			JourneyVO journey = new JourneyVO(journeyJson);
			travelJarVO.setJourney(journey);
			System.out.println("Journey name: " + journey.getName());
			
			JSONObject friendsJson =  mainJson.getJSONObject("friends");
			if ( friendsJson != null ) {
				JSONArray friendsArray = friendsJson.getJSONArray("friend_item");
				if ( friendsArray!=null && friendsArray.length()>0 ) {
					Vector<FriendVO> friendsVector = new Vector<FriendVO>();
					for (int i=0; i<friendsArray.length(); i++) {
						JSONObject friendObject = (JSONObject) friendsArray.getJSONObject(i);
						FriendVO friend = new FriendVO(friendObject);
						friendsVector.add(friend);
					}
					travelJarVO.setFriendsVector(friendsVector);
				}
			}
			
			JSONObject milestonesJson =  mainJson.getJSONObject("milestone");
			if ( milestonesJson != null ) {
				JSONArray stonesArray = milestonesJson.getJSONArray("stone");
				if ( stonesArray!=null && stonesArray.length()>0 ) {
					Vector<StoneVO> stonesVector = new Vector<StoneVO>();
					for (int i=0; i<stonesArray.length(); i++) {
						JSONObject stoneObject = (JSONObject) stonesArray.getJSONObject(i);
						StoneVO stone = new StoneVO(stoneObject);
						stonesVector.add(stone);
					}
					travelJarVO.setMilestonesVector(stonesVector);
				}
			}
			
			
		}catch (Exception e) {
			LogUtility.printLog("Exception while printing json " + e);
		}
		
		
		return travelJarVO;
	}
	
	public void createAnimations(TravelJarVO travelJar) {
		
		if ( travelJar!=null ) {
			
			List <Animation> animations =  new ArrayList<Animation>();
			
//			journey intro starts here, may be we should think of applying css
			JourneyVO journey = travelJar.getJourney();
			if ( journey != null ) {
				
				Animation animation = new Animation();
				animation.setStart(0);
				animation.setEnd(5000);
				List<Element> elementsList = new ArrayList<Element>();
				Element element = new Element();
				element.setType("image");
				element.setDescription("background.jpg");
				element.setWidth(480);
				element.setHeight(360);
				element.setStartPosition("0,0");
				element.setEndPosition("0,0");
				element.setEffect("");
				element.setColor("white");
				elementsList.add(element);
				animation.setElements(elementsList);
				animations.add(animation);
				
				animation = new Animation();
				animation.setStart(0);
				animation.setEnd(5000);
				elementsList = new ArrayList<Element>();
				element = new Element();
				element.setType("text");
				element.setDescription(journey.getName());
				element.setHeight(20);
				element.setEffect("middle");
				element.setStartPosition("-0.2,0.3");
				element.setEndPosition("0.2,0.3");
				element.setColor("black");
				element.setSpeedX(20);
			
				elementsList.add(element);
				animation.setElements(elementsList);
				animations.add(animation);
				
				animation = new Animation();
				animation.setStart(1000);
				animation.setEnd(5000);
				elementsList = new ArrayList<Element>();
				element = new Element();
				element.setType("text");
				element.setDescription(journey.getTagLine());
				element.setHeight(20);
				element.setEffect("middle");
				element.setStartPosition("-0.2,0.4");
				element.setEndPosition("0.2,0.4");
				element.setSpeedX(20);
				element.setColor("black");
				elementsList.add(element);
				animation.setElements(elementsList);
				animations.add(animation);
				
				animation = new Animation();
				animation.setStart(2000);
				animation.setEnd(5000);
				elementsList = new ArrayList<Element>();
				element = new Element();
				element.setType("text");
				element.setDescription( journey.getStartDate() + " - " + journey.getEndDate() );
				element.setHeight(20);
				element.setEffect("leftMargin");
				element.setStartPosition("-0.2,0.9");
				element.setEndPosition("0.2,0.9");
				element.setSpeedX(20);
				element.setColor("black");
				elementsList.add(element);
				animation.setElements(elementsList);
				animations.add(animation);
				
				animation = new Animation();
				animation.setStart(1500);
				animation.setEnd(5000);
				elementsList = new ArrayList<Element>();
				element = new Element();
				element.setType("text");
				element.setDescription("Jaipur");
				element.setHeight(20);
				element.setEffect("rightMargin");
				element.setStartPosition("-0.2,0.9");
				element.setEndPosition("0.2,0.9");
				element.setSpeedX(20);
				element.setColor("black");
				elementsList.add(element);
				animation.setElements(elementsList);
				animations.add(animation);
				
			}
//			journey intro ends here
			
			
//			people intro starts here
			
			Vector friendsVector = travelJar.getFriendsVector();
			if ( friendsVector!=null && friendsVector.size()>0 ) {
				
				Animation animation = new Animation();
				animation.setStart(5000);
				animation.setEnd(13000);
				List<Element> elementsList = new ArrayList<Element>();
				Element element = new Element();
				element.setType("text");
				element.setDescription("Memory Creators");
				element.setHeight(20);
				element.setEffect("middle");
				element.setStartPosition("0.4,-0.5");
				element.setEndPosition("0.4,0.2");
				element.setSpeedX(0);
				element.setSpeedY(20);
				element.setColor("black");
				elementsList.add(element);
				animation.setElements(elementsList);
				animations.add(animation);
				
				if ( friendsVector.size() <=4 ) {
				
					int gapX = (int) ( ( Data.width - ( friendsVector.size()*60 ) ) / (friendsVector.size()+1) ); 
					
					for ( int i=0; i<friendsVector.size(); i++) {
						
						FriendVO friend = (FriendVO) friendsVector.get(i);
						animation = new Animation();
						animation.setStart(1000);
						animation.setEnd(5000);
						elementsList = new ArrayList<Element>();
						element = new Element();
						element.setType("image");
						element.setDescription("005.PNG");
						element.setWidth(60);
						element.setHeight(60);
						element.setEffect("");
						element.setClip(true);
						element.setStartPosition("0,0.4");
						element.setEndPosition("0.1,0.4");
						element.setSpeedX(20);
						element.setSpeedY(0);
						element.setColor("black");
						elementsList.add(element);
						animation.setElements(elementsList);
						animations.add(animation);
						
						element = new Element();
						element.setType("text");
						element.setDescription(journey.getTagLine());
						element.setHeight(20);
						element.setEffect("middle");
						element.setStartPosition("-0.2,0.4");
						element.setEndPosition("0.2,0.4");
						element.setSpeedX(20);
						element.setColor("black");
						elementsList.add(element);
						animation.setElements(elementsList);
						animations.add(animation);
						
						
					}
					
				}
				
				
				
				
			}
			
//			people intro ends here
			
			
//			refering animations for video creation
			Data.animations = animations;
			
		}
		
	
		
	}
	

}
