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
import com.traveljar.vo.PictureVO;
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
	
	public List <Animation> createAnimations(TravelJarVO travelJar) {
		
		if ( travelJar!=null ) {
			
//			objects required for creation of animations
			List <Animation> animations =  new ArrayList<Animation>();
			Animation animation = new Animation();
			List<Element> elementsList = new ArrayList<Element>();
			Element element = new Element();
			
//			journey intro starts here, may be we should think of applying css
			JourneyVO journey = travelJar.getJourney();
			if ( journey != null ) {
				
				animation = new Animation();
				animation.setStart(0);
				animation.setEnd(5000);
				elementsList = new ArrayList<Element>();
				element = new Element();
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
			Vector friendsVector = travelJar.getFriendsVector();
			if ( friendsVector != null ) {
				
				if ( friendsVector.size()>0 ) {
					
//					for 4 maximum in one row
					if ( friendsVector.size() <=4 ) {
						
						int xGap = (int)( ( Data.width - ( 60*friendsVector.size() ) ) / ( friendsVector.size()+1) );
						animation.setStart(5000);
						animation.setEnd(15000);
						elementsList = new ArrayList<Element>();
						element = new Element();
						element.setType("text");
						element.setDescription("Memory Creators");
						element.setHeight(20);
						element.setStartPosition("0.4,-0.5");
						element.setEndPosition("0.4,0.2");
						element.setSpeedX(0);
						element.setSpeedY(20);
						element.setEffect("");
						element.setColor("Black");
						elementsList.add(element);
						animation.setElements(elementsList);
						animations.add(animation);
						
						
						for ( int i=0; i<friendsVector.size(); i++ ) {
							
							FriendVO friend = (FriendVO) friendsVector.get(i);
							int xCoordinate = ( ( (i+1)*xGap ) + ( (i)*60  )  );
							
							animation = new Animation();
							animation.setStart( 6000 + (i*2000) );
							animation.setEnd(15000);
							elementsList = new ArrayList<Element>();
							element = new Element();
							element.setType("image");
							element.setDescription(friend.getProfileImagePath());
							element.setWidth(60);
							element.setHeight(60);
							element.setStartPosition("0,0.4");
							element.setEndPosition("0.1,0.4");
							element.setSpeedX(20);
							element.setSpeedY(0);
							element.setClip(true);
							element.setEffect("boxMiddle;"  + xCoordinate + ";");
							element.setColor("black");
							elementsList.add(element);
							
							
							element = new Element();
							element.setType("text");
							element.setDescription(friend.getName());
							element.setHeight(20);
							element.setStartPosition("-0.1,0.65");
							element.setEndPosition("0.1,0.65");
							element.setSpeedX(20);
							element.setSpeedY(0);
							element.setEffect("boxMiddle;" + xCoordinate + ";");
							element.setColor("black");
							elementsList.add(element);
							animation.setElements(elementsList);
							animations.add(animation);
							
						}
						
					}
//					may continue and change row	
				}
			}
			
//			slide animation starts here
			animation.setName("slide");
			animation.setStart(14000);
			animation.setEnd(15000);
			animation.setBackground("gray");
			animations.add(animation);
//			slide animation ends here
			
			
//			people intro starts here
			Vector milestonesVector = travelJar.getMilestonesVector();
			if ( milestonesVector != null ) {
			
				if ( ( milestonesVector.size()>1 ) && ( milestonesVector.size()<=4 ) ) {
					
					animation = new Animation();
					animation.setStart(15000);
					animation.setEnd(35000);
					elementsList = new ArrayList<Element>();
					element = new Element();
					element.setType("text");
					element.setDescription("Milestones Title");
					element.setHeight(15);
					element.setStartPosition("0.4, 0.1");
					element.setEndPosition("0.4, 0.1");
					element.setSpeedX(0);
					element.setSpeedY(10);
					element.setEffect("middle");
					element.setColor("Black");
					elementsList.add(element);
					animation.setElements(elementsList);
					animations.add(animation);
					
					
					int pictureTimeGap = 15000;
					
					for ( int i=0; i<milestonesVector.size(); i++) {
						
						StoneVO stone =  (StoneVO) milestonesVector.get(i);
						
						animation = new Animation();
						animation.setStart(pictureTimeGap);
						pictureTimeGap += 1000;
						System.out.println("Picture Gap Time: " + pictureTimeGap);
						animation.setEnd(35000);
						elementsList = new ArrayList<Element>();
						element = new Element();
						element.setType("image");
						element.setDescription("jaipur.jpg");
						element.setWidth(30);
						element.setHeight(30);
						element.setStartPosition("0.1,0.4");
						element.setEndPosition("0.1,0.4");
						element.setSpeedX(0);
						element.setSpeedY(0);
						element.setEffect("fade;0-0.1-0.2-0.3-0.4-0.5-0.6-0.7-0.8-0.9-1");
						element.setColor("white");
						elementsList.add(element);
						
						element = new Element();
						element.setType("text");
						element.setDescription(stone.getName());
						element.setHeight(15);
						element.setStartPosition("0.1,0.55");
						element.setEndPosition("0.1,0.55");
						element.setSpeedX(0);
						element.setSpeedY(0);
						element.setEffect("fade;0-0.1-0.2-0.3-0.4-0.5-0.6-0.7-0.8-0.9-1");
						element.setColor("black");
						elementsList.add(element);
						animation.setElements(elementsList);
						animations.add(animation);
						
						Vector<PictureVO> picturesVector = stone.getPictures();
						if ( picturesVector != null ) {
							
							if ( picturesVector.size()>0 ) {
								for ( int j=0; j<picturesVector.size(); j++) {
									PictureVO picture = (PictureVO) picturesVector.get(j);
									if ( picture!= null ) {
										
										animation = new Animation();
										animation.setStart(pictureTimeGap );
										pictureTimeGap += 2000;
										animation.setEnd(35000);
										elementsList = new ArrayList<Element>();
										element = new Element();
										element.setType("image");
										element.setDescription("002.PNG");
										element.setWidth(480);
										element.setHeight(360);
										element.setStartPosition("0.2,0.3");
										element.setEndPosition("0.2,0.3");
										element.setSpeedX(0);
										element.setSpeedY(0);
										element.setEffect("middle");
										element.setColor("white");
										elementsList.add(element);
										
										element = new Element();
										element.setType("text");
										element.setDescription(picture.getDate());
										element.setWidth(30);
										element.setHeight(30);
										element.setStartPosition("0.05,0.9");
										element.setEndPosition("0.05,0.9");
										element.setSpeedX(0);
										element.setSpeedY(10);
										element.setEffect("leftMargin");
										element.setColor("white");
										elementsList.add(element);
										
										element = new Element();
										element.setType("text");
										element.setDescription(picture.getPlace());
										element.setHeight(20);
										element.setStartPosition("0.8,0.9");
										element.setEndPosition("0.8,0.9");
										element.setSpeedX(0);
										element.setSpeedY(10);
										element.setEffect("rightMargin");
										element.setColor("white");
										elementsList.add(element);
										animation.setElements(elementsList);
										animations.add(animation);
										
									}
									
								}
							}
							
						}
						
					}
					
				}
//				atleast 2 milestones are required for this and check for more than one row
			}
//			people intro ends here
			
			
			return animations;
		}
//		returns created animations for video
		
		return null;
		
	}
	

}
