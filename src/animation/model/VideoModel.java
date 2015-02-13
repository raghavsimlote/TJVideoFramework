package animation.model;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import org.json.me.JSONArray;
import org.json.me.JSONObject;

import animation.utility.LogUtility;
import animation.utility.TextUtility;

import com.traveljar.vo.FriendVO;
import com.traveljar.vo.JourneyVO;
import com.traveljar.vo.MoodVO;
import com.traveljar.vo.NoteVO;
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
			int videoTime = 0;

//			journey introduction starts here, may be we should think of applying css
			JourneyVO journey = travelJar.getJourney();
			if ( journey != null ) {
				videoTime = createJourneyAnimations(animations, videoTime, journey);
			}
//			journey introduction ends here
			
//			friends introduction starts here
			Vector<FriendVO> friendsVector = travelJar.getFriendsVector();
			if ( friendsVector != null ) {
				if ( friendsVector.size()>0 ) {
					videoTime = createFriendsAnimations(animations, videoTime, friendsVector);
				}
			}
//			friends introduction ends here
			
//			gray color slide animation starts here
//			videoTime = createSlideAnimations(animations, videoTime, "gray");
//			gray color slide animation ends here
			
//			white color slide animation starts here
//			videoTime = createSlideAnimations(animations, videoTime, "white");
//			white color slide animation ends here
			
//			milestones starts here
			Vector<StoneVO> milestonesVector = travelJar.getMilestonesVector();
			if ( milestonesVector != null ) {
				if ( milestonesVector.size()>0 ) {
					String titleOfJourney = "NOT PROVIDED";
					if ( journey != null ) {
						titleOfJourney = journey.getName();
					}
					videoTime = createMilestonesAnimations(animations, videoTime, milestonesVector, friendsVector, titleOfJourney );
				}
			}
//			milestones ends here
						
			return animations;
		}
		
		return null;
		
	}
	
	public int createJourneyAnimations(List <Animation> animations, int videoTime, JourneyVO journey) {
		
		Animation animation = new Animation();
		List<Element> elementsList = new ArrayList<Element>();
		Element element = new Element();
		int animationCounter = 0;
		
		animation = new Animation();
		animation.setStart(videoTime);
		animation.setEnd(5000);
		animation.setBackground("white");
		elementsList = new ArrayList<Element>();
		element = new Element();
		element.setType("image");
		element.setDescription(journey.getBgImagePath());
		element.setWidth(Data.width);
		element.setHeight(Data.height);
		element.setStartPosition("0,0");
		element.setEndPosition("0,0");
		element.setEffect("");
		element.setColor("white");
		elementsList.add(element);
		animation.setElements(elementsList);
		animations.add(animation);
		
		animationCounter++;
		animation = new Animation();
		animation.setStart(videoTime);
		animation.setEnd(5000);
		elementsList = new ArrayList<Element>();
		element = new Element();
		element.setType("text");
		element.setDescription(journey.getName());
		element.setHeight(30);
		element.setEffect("middle;font;bold;");
		element.setStartPosition("-0.2,0.3");
		element.setEndPosition("0.2,0.3");
		element.setColor("white");
		element.setSpeedX(50);
	
		elementsList.add(element);
		animation.setElements(elementsList);
		animations.add(animation);
		
		animationCounter++;
		animation = new Animation();
		videoTime+=1000;
		animation.setStart(videoTime);
		animation.setEnd(5000);
		elementsList = new ArrayList<Element>();
		element = new Element();
		element.setType("text");
		element.setDescription(journey.getTagLine());
		element.setHeight(25);
		element.setEffect("middle;font;italic;");
		element.setStartPosition("-0.2,0.4");
		element.setEndPosition("0.2,0.4");
		element.setSpeedX(50);
		element.setColor("white");
		elementsList.add(element);
		animation.setElements(elementsList);
		animations.add(animation);
		
		animationCounter++;
		animation = new Animation();
		videoTime+=1000;
		animation.setStart(videoTime);
		animation.setEnd(5000);
		elementsList = new ArrayList<Element>();
		element = new Element();
		element.setType("text");
		element.setDescription( journey.getNumberOfDays() + " Days");
		element.setHeight(25);
		element.setEffect("leftMargin;30;");
		element.setStartPosition("-0.2,0.9");
		element.setEndPosition("0.2,0.9");
		element.setSpeedX(50);
		element.setColor("white");
		elementsList.add(element);
		animation.setElements(elementsList);
		animations.add(animation);
		
		animationCounter++;
		animation = new Animation();
		videoTime-=500;
		animation.setStart(videoTime);
		animation.setEnd(5000);
		elementsList = new ArrayList<Element>();
		element = new Element();
		element.setType("text");
		Vector places = journey.getPlacesList();
		if ( places != null && places.size()>0 ) {
			if ( places.size() > 1) {
				element.setDescription( (String) journey.getPlacesList().get(1) );
			} else 
			{
				element.setDescription( (String) journey.getPlacesList().get(0) );
			}
		} else {
			element.setDescription( "" );
		}
		
		element.setHeight(25);
		element.setEffect("rightMargin;30;");
		element.setStartPosition("-0.2,0.9");
		element.setEndPosition("0.2,0.9");
		element.setSpeedX(50);
		element.setColor("white");
		elementsList.add(element);
		animation.setElements(elementsList);
		animations.add(animation);
		
		videoTime+=3000;
		
//		setting end time
		if ( animations.size()-animationCounter >= 0 ) {
			for ( int i=0; i<animations.size(); i++ ) {
				animation = (Animation) animations.get(i);
				animation.setEnd(videoTime);
			}
		}
		
		return videoTime;
		
	}
	
	
	public int createFriendsAnimations(List <Animation> animations, int videoTime, Vector<FriendVO> friendsVector) {
		
		Animation animation = new Animation();
		List<Element> elementsList = new ArrayList<Element>();
		Element element = new Element();
		int animationCounter = 0;
		
//		for 4 maximum in one row
		if ( friendsVector.size() <=4 ) {
			
			int xGap = (int)( ( Data.width - ( 60*friendsVector.size() ) ) / ( friendsVector.size()+1) );
			animationCounter++;
			animation = new Animation();
			animation.setStart(videoTime);
			videoTime+=2000;
			animation.setEnd(15000);
			elementsList = new ArrayList<Element>();
			element = new Element();
			element.setType("text");
			element.setDescription("Memory Creators");
			element.setHeight(30);
			element.setStartPosition("0.4,-0.5");
			element.setEndPosition("0.4,0.2");
			element.setSpeedX(0);
			element.setSpeedY(50);
			element.setEffect("middle");
			element.setColor("Black");
			elementsList.add(element);
			animation.setElements(elementsList);
			animations.add(animation);
			
			
			for ( int i=0; i<friendsVector.size(); i++ ) {
				
				FriendVO friend = (FriendVO) friendsVector.get(i);
				int xCoordinate = ( ( (i+1)*xGap ) + ( (i)*60  )  );
				animationCounter++;
				animation = new Animation();
				animation.setStart( videoTime );
				videoTime += 2000;
				animation.setEnd(15000);
				elementsList = new ArrayList<Element>();
				element = new Element();
				element.setType("image");
				element.setDescription(friend.getProfileImagePath());
				element.setWidth(60);
				element.setHeight(60);
				element.setStartPosition("0,0.4");
				element.setEndPosition("0.1,0.4");
				element.setSpeedX(50);
				element.setSpeedY(0);
				element.setClip(true);
				element.setEffect("boxMiddle;"  + xCoordinate + ";");
				element.setColor("black");
				elementsList.add(element);
				
				
				element = new Element();
				element.setType("text");
				element.setDescription(friend.getName());
				element.setHeight(25);
				element.setStartPosition("-0.1,0.58");
				element.setEndPosition("0.1,0.58");
				element.setSpeedX(45);
				element.setSpeedY(0);
				element.setEffect("boxMiddle;" + xCoordinate + ";");
				element.setColor("black");
				elementsList.add(element);
				animation.setElements(elementsList);
				animations.add(animation);
				
			}
			
		}
//		may continue and change row	
		
		
		
//		setting end time
		if ( animations.size()-animationCounter >= 0 ) {
			for ( int i=animations.size()-animationCounter; i<animations.size(); i++ ) {
				animation = (Animation) animations.get(i);
				animation.setEnd(videoTime);
			}
		}
		
		return videoTime;
		
	}
	
	public int createSlideAnimations(List <Animation> animations, int videoTime, String color) {
			
			Animation animation = new Animation();
			
			animation.setName("slide");
			animation.setStart(videoTime);
			videoTime += 500;
			animation.setEnd(videoTime);
			animation.setBackground(color);
			animations.add(animation);
			
			return videoTime;
		
	}
	
	public int createMilestonesAnimations(List <Animation> animations, int videoTime, Vector<StoneVO> milestonesVector, Vector<FriendVO> friendsVector, String titleOfJourney ) {
		
		List<Element> elementsList = new ArrayList<Element>();
		Element element = new Element();
		
		if ( ( milestonesVector.size()>1 ) && ( milestonesVector.size()<=4 ) ) {
			
			Vector<Animation> stoneAnimations = new Vector<Animation>();
			Vector<Animation> carAnimations =  new Vector<Animation>();

			int totalWidth = 0;
			
			Animation milestonesAnimation = new Animation();
			milestonesAnimation.setStart(videoTime);
			milestonesAnimation.setEnd(35000);
			milestonesAnimation.setBackground("white");
			elementsList = new ArrayList<Element>();
			element = new Element();
			element.setType("text");
			element.setDescription(titleOfJourney);
			element.setHeight(30);
			element.setStartPosition("0.4, 0.1");
			element.setEndPosition("0.4, 0.1");
			element.setSpeedX(0);
			element.setSpeedY(10);
			element.setEffect("middle");
			element.setColor("Black");
			elementsList.add(element);
			milestonesAnimation.setElements(elementsList);
			animations.add(milestonesAnimation);
			
			
			for ( int i=0; i<milestonesVector.size(); i++) {
				
				StoneVO stone =  (StoneVO) milestonesVector.get(i);
				
				Animation stoneAnimation = new Animation();
//				pictureTimeGap += 5000;
				stoneAnimation.setStart(videoTime);
				System.out.println("Picture Gap Time: " + videoTime);
				stoneAnimation.setEnd(35000);
				elementsList = new ArrayList<Element>();
				element = new Element();
				element.setType("image");
				String iconPathString = TextUtility.getIconPath(stone.getType());
				System.out.println("Icon path for milestone: " + iconPathString);
				element.setDescription(iconPathString);
				element.setWidth(64);
				element.setHeight(64);
				element.setStartPosition("0.0,0.4");
				element.setEndPosition("0.1,0.4");
				element.setSpeedX(20);
				element.setSpeedY(0);
				element.setEffect("");
//				element.setEffect("fade;0-0.1-0.2-0.3-0.4-0.5-0.6-0.7-0.8-0.9-1");
				element.setColor("white");
				elementsList.add(element);
				
				element = new Element();
				element.setType("text");
				element.setDescription(stone.getName());
				element.setHeight(20);
				
//				count the total width
				Text label = new Text(stone.getName());
				label.setFont(Font.font(null, FontWeight.SEMI_BOLD, element.getHeight()));
				int width  =  (int) label.getLayoutBounds().getWidth();
				element.setWidth(width);
				totalWidth += label.getLayoutBounds().getWidth();
//				System.out.println("Width is " + totalWidth);
				
				element.setStartPosition("0.0,0.55");
				element.setEndPosition("0.1,0.55");
				element.setSpeedX(20);
				element.setSpeedY(0);
				element.setEffect("");
//				element.setEffect("fade;0-0.1-0.2-0.3-0.4-0.5-0.6-0.7-0.8-0.9-1");
				element.setColor("black");
				elementsList.add(element);
				stoneAnimation.setElements(elementsList);
				animations.add(stoneAnimation);
				
//				add to the vector for future reference
				stoneAnimations.add(stoneAnimation);
				
				if ( (i>0) ) {
					
					Animation carAnimation = new Animation();
					carAnimation.setBackground("white");
					videoTime += 2000;
					carAnimation.setStart(videoTime);
					videoTime += 2000;
					carAnimation.setEnd(videoTime);
					elementsList = new ArrayList<Element>();
					element = new Element();
					element.setType("image");
					String carIconPathString = TextUtility.getIconPath("car");
					element.setDescription( carIconPathString );
					element.setWidth(64);
					element.setHeight(64);
					element.setEffect("");
//					element.setEffect("Lmove;0.17-0.2-0.5-0.6-0.8-0.9-1-1.001-1");
					element.setStartPosition("0.00,0.45");
					element.setEndPosition("0.00,0.45");
					element.setSpeedX(30);
					element.setSpeedY(0);
					element.setColor("white");
					element.setClip(false);
					elementsList.add(element);
					
//					element = new Element();
//					element.setType("path");
//					element.setDescription( "footsteps.png" );
//					element.setWidth(20);
//					element.setHeight(20);
//					element.setEffect("feet;0");
//					element.setCurrentPosition("0.00,0.45");
//					element.setStartPosition("0.00,0.45");
//					element.setEndPosition("0.00,0.45");
//					element.setSpeedX(5);
//					element.setSpeedY(0);
//					element.setColor("white");
//					element.setClip(false);
//					elementsList.add(element);
					
					carAnimation.setElements(elementsList);
					animations.add(carAnimation);
					carAnimations.add(carAnimation);
					
				}
//				car image with path
				
//				milestones pictures animations starts here
				Vector<PictureVO> picturesVector = stone.getPictures();
				if ( picturesVector != null ) {
					if ( picturesVector.size()>0 ) {
						videoTime = createMilestonesPicturesAnimations(animations, videoTime, picturesVector);
					}
				}
//				milestones pictures animations ends here
				
//				milestones notes animations starts here
				Vector<NoteVO> notesVector = stone.getNotes();
				
				if ( notesVector != null ) {
					if ( notesVector.size()>0 ) {
						videoTime = createMilestonesNotesAnimations(animations, videoTime, notesVector);
					}
				}
//				milestones notes animations ends here
				
//				milestones moods animations starts here
				Vector<MoodVO> moodsVector = stone.getMoods();
				if ( moodsVector != null ) {
					if ( moodsVector.size()>0 ) {
						videoTime = createMilestonesMoodsAnimations(animations, videoTime, moodsVector, friendsVector);
					}
				}
//				milestones moods animations ends here
				
				
			}
//			outer loop ends here
			
//			setting end time, width, height of  stones elements and car animations
			videoTime += 2000;
			milestonesAnimation.setEnd(videoTime);
			int xGap = (int) ( (Data.width-totalWidth) / (stoneAnimations.size()+1) );
			int tempWidth = 0;
			for (int i=0; i<stoneAnimations.size() ;i++) {
				
				Animation stoneAnimation= (Animation) stoneAnimations.get(i);
				elementsList = stoneAnimation.getElements();
				
				element = (Element) elementsList.get(1);
				int xPos = ((i+1)*xGap) + tempWidth ;
				int width = element.getWidth();
				tempWidth += width;
				element.setEffect(element.getEffect() + ";textXPosition;" + xPos + ";");
				element = (Element) elementsList.get(0);
				
				element.setEffect(element.getEffect() + ";ImageXPosition;" + ( xPos +( ( width-element.getWidth() )/2 ) )  +";");
				
				stoneAnimation.setEnd(videoTime);
			
				
				if ( stoneAnimations.size() > 1 ) {
					
					if ( i==0 ) {
						
						Animation carAnimation = (Animation) carAnimations.get(i);
						elementsList = carAnimation.getElements();
						carAnimation.setEnd(videoTime);
						element = elementsList.get(0);
						element.setEffect(element.getEffect() + ";VehicleXStartPosition;" + ( xPos + width )  + ";");
						
//						element = elementsList.get(1);
//						element.setEffect(element.getEffect() + ";VehicleXStartPosition;" + ( xPos + width )  + ";");
						
					} else if ( ( (i+1) < stoneAnimations.size() ) ) {
						
						Animation carAnimation = (Animation) carAnimations.get(i-1);
						elementsList = carAnimation.getElements();
						carAnimation.setEnd(videoTime);
						element = elementsList.get(0);
						element.setEffect(element.getEffect() + ";VehicleXStopPosition;" + ( xPos - element.getWidth() )  + ";");
						
//						element = elementsList.get(1);
//						element.setEffect(element.getEffect() + ";VehicleXStopPosition;" + ( xPos - element.getWidth() )  + ";");
						
						carAnimation = (Animation) carAnimations.get(i);
						elementsList = carAnimation.getElements();
						carAnimation.setEnd(videoTime);
						element = elementsList.get(0);
						element.setEffect(element.getEffect() + ";VehicleXStartPosition;" + ( xPos + width )  + ";");
						
//						element = elementsList.get(1);
//						element.setEffect(element.getEffect() + ";VehicleXStartPosition;" + ( xPos + width )  + ";");
						
					} else if ( ( (i+1) == stoneAnimations.size() ) ) {
						
						Animation carAnimation = (Animation) carAnimations.get(i-1);
						elementsList = carAnimation.getElements();
						element = elementsList.get(0);
						element.setEffect(element.getEffect() + ";VehicleXStopPosition;" + ( xPos - element.getWidth() )  + ";");
						
//						element = elementsList.get(1);
//						element.setEffect(element.getEffect() + ";VehicleXStopPosition;" + ( xPos - element.getWidth() )  + ";");
					}
						
				}
				
			}
			
			
			
//			set end time of stone animations same as milestone time
			
		}
		else if ( ( milestonesVector.size()>4 ) && ( milestonesVector.size()<=8 ) ) {
			
		}
		
		
		return videoTime;
	}
	
	
	public int createMilestonesPicturesAnimations(List<Animation> animations, int videoTime, Vector<PictureVO> picturesVector) {
		
		Animation animation = new Animation();
		List<Element> elementsList = new ArrayList<Element>();
		Element element = new Element();
		
//		loop starts here
		for ( int j=0; j<picturesVector.size(); j++) {
			PictureVO picture = (PictureVO) picturesVector.get(j);
			if ( picture!= null ) {
				
				animation = new Animation();
				videoTime += 2000;
				animation.setStart(videoTime );
				animation.setEnd( videoTime + 2000 );
				animation.setBackground("white");
				elementsList = new ArrayList<Element>();
				
				element = new Element();
				element.setType("image");
				element.setDescription(picture.getPath());
				element.setWidth(Data.width);
				element.setHeight(Data.height);
				element.setStartPosition("0.2,0.3");
				element.setEndPosition("0.2,0.3");
				element.setSpeedX(0);
				element.setSpeedY(0);
				element.setClip(false);
				element.setEffect("middle;setOpacity;0.3");
				element.setColor("white");
				elementsList.add(element);
				
				element = new Element();
				element.setType("image");
				element.setDescription(picture.getPath());
				element.setWidth((Data.width*2)/3);
				element.setHeight((Data.height*2)/3);
				element.setStartPosition("0.2,0.3");
				element.setEndPosition("0.2,0.3");
				element.setSpeedX(0);
				element.setSpeedY(0);
				element.setClip(false);
				element.setEffect("middle;borderImage");
				element.setColor("white");
				elementsList.add(element);
				
				element = new Element();
				element.setType("text");
				element.setDescription(picture.getDate());
				element.setHeight(25);
				element.setStartPosition("0.05,0.9");
				element.setEndPosition("0.05,0.9");
				element.setSpeedX(0);
				element.setSpeedY(0);
				element.setEffect("leftBelowImage;" + ((Data.width*2)/3) + ";" + ((Data.height*2)/3) + ";25;" );
				element.setColor("BLACK");
				elementsList.add(element);
				
				element = new Element();
				element.setType("text");
				element.setDescription(picture.getPlace());
				element.setHeight(25);
				element.setStartPosition("0.0,0.9");
				element.setEndPosition("0.0,0.9");
				element.setSpeedX(0);
				element.setSpeedY(0);
				element.setEffect("rightBelowImage;" + ((Data.width*2)/3) + ";" + ((Data.height*2)/3) + ";25;" );
				element.setColor("BLACK");
				elementsList.add(element);
				animation.setElements(elementsList);
				animations.add(animation);
				
			}
			
		}
//		loop ends here
		videoTime += 2000;
		return videoTime;
		
	}
	
	public int createMilestonesNotesAnimations(List<Animation> animations, int videoTime, Vector<NoteVO> notesVector) {
		
		Animation animation = new Animation();
		List<Element> elementsList = new ArrayList<Element>();
		Element element = new Element();
		
//		loop starts here
		for ( int j=0; j<notesVector.size(); j++) {
			NoteVO note = (NoteVO) notesVector.get(j);
			if ( note!= null ) {

				animation = new Animation();
				videoTime += 2000;
				animation.setStart(videoTime );
				animation.setEnd( videoTime + 4000 );
				animation.setBackground("NotesColor");
				elementsList = new ArrayList<Element>();
				
				element = new Element();
				element.setType("text");
				element.setDescription(note.getDescription());
				
				Text label = new Text(note.getDescription());
				label.setFont(Font.font(null, FontWeight.SEMI_BOLD, element.getHeight()));
				int width  =  (int) label.getLayoutBounds().getWidth();
				element.setWidth(width);
				element.setHeight(25);
				element.setStartPosition("0.2,0.3");
				element.setEndPosition("0.2,0.3");
				element.setSpeedX(0);
				element.setSpeedY(0);
				element.setClip(false);
				element.setEffect("middle;");
				element.setColor("black");
				elementsList.add(element);
				
				element = new Element();
				element.setType("line");
				element.setDescription("Not Applicable");
				element.setWidth(width);
				element.setHeight(2);
				element.setStartPosition("0.2,0.35");
				element.setEndPosition("0.2,0.35");
				element.setSpeedX(0);
				element.setSpeedY(0);
				element.setClip(false);
				element.setEffect("middle;");
				element.setColor("black");
				elementsList.add(element);
				
				element = new Element();
				element.setType("text");
				element.setDescription(note.getDate());
				element.setHeight(25);
				element.setStartPosition("0.2,0.40");
				element.setEndPosition("0.2,0.40");
				element.setSpeedX(0);
				element.setSpeedY(0);
				element.setEffect("leftBelowLine;" + (width) + ";25;" );
				element.setColor("black");
				elementsList.add(element);
				
				element = new Element();
				element.setType("text");
				element.setDescription(note.getPlace());
				element.setHeight(25);
				element.setStartPosition("0.0,0.40");
				element.setEndPosition("0.0,0.40");
				element.setSpeedX(0);
				element.setSpeedY(0);
				element.setEffect("rightBelowLine;" + (width) + ";25;" );
				element.setColor("black");
				elementsList.add(element);
				animation.setElements(elementsList);
				animations.add(animation);
				
			}
			
		}
//		loop ends here
		videoTime += 4000;
		return videoTime;
		
	}

public int createMilestonesMoodsAnimations(List<Animation> animations, int videoTime, Vector<MoodVO> moodsVector, Vector<FriendVO> friendsVector) {
	
	Animation animation = new Animation();
	List<Element> elementsList = new ArrayList<Element>();
	Element element = new Element();
	
//	loop starts here
	for ( int j=0; j<moodsVector.size(); j++) {
		MoodVO mood = (MoodVO) moodsVector.get(j);
		if ( mood!= null ) {
			
			animation = new Animation();
			videoTime += 2000;
			animation.setStart(videoTime );
			animation.setEnd( videoTime + 4000 );
			animation.setBackground("NotesColor");
			elementsList = new ArrayList<Element>();
			
			element = new Element();
			element.setType("image");
			element.setDescription("resources/images/friends/005.PNG");
			String iconPathString = TextUtility.getImagePathFromFriendId(friendsVector, mood.getPerson());
			System.out.println("Icon path for Mood: " + iconPathString);
			if ( iconPathString != null ) {
				element.setDescription(iconPathString);
			}
			element.setWidth(64);
			element.setHeight(64);
			element.setStartPosition("0.3,0.3");
			element.setEndPosition("0.3,0.3");
//			element.setSpeedX(20);
//			element.setSpeedY(0);
			element.setEffect("widthMiddle;");
//			element.setEffect("fade;0-0.1-0.2-0.3-0.4-0.5-0.6-0.7-0.8-0.9-1");
			element.setColor("white");
			elementsList.add(element);
			
			element = new Element();
			element.setType("text");
			element.setDescription(mood.getPerson() + " is");
			element.setHeight(20);
			
//			count the total width
			Text label = new Text(mood.getPerson() + " is");
			label.setFont(Font.font(null, FontWeight.SEMI_BOLD, element.getHeight()));
			int width  =  (int) label.getLayoutBounds().getWidth();
			element.setWidth(width);
			double totalWidth = label.getLayoutBounds().getWidth();
//			System.out.println("Width is " + totalWidth);
			
			element.setStartPosition("0.3,0.50");
			element.setEndPosition("0.3,0.50");
			element.setSpeedX(20);
			element.setSpeedY(0);
			element.setEffect("widthMiddle;");
//			element.setEffect("fade;0-0.1-0.2-0.3-0.4-0.5-0.6-0.7-0.8-0.9-1");
			element.setColor("BLACK");
			elementsList.add(element);
		
			element = new Element();
			element.setType("image");
			element.setDescription("resources/images/icons/sedan2.png");
			String emotIconPathString = TextUtility.getEmotIconPath(mood.getMood());
			if ( emotIconPathString!=null ) {
				element.setDescription(emotIconPathString);
			}
			element.setHeight(30);
			element.setEffect("widthMiddle;");
			element.setStartPosition("0.3,0.52");
			element.setEndPosition("0.3,0.52");
			element.setColor("BLACK");
//			element.setSpeedX(50);
			elementsList.add(element);
			animation.setElements(elementsList);
			animations.add(animation);
			
			element = new Element();
			element.setType("text");
			element.setDescription(mood.getReason());
			element.setHeight(25);
			element.setEffect("widthMiddle;");
			element.setStartPosition("0.3,0.56");
			element.setEndPosition("0.3,0.56");
//			element.setSpeedX(50);
			element.setColor("BLACK");
			elementsList.add(element);
			animation.setElements(elementsList);
			animations.add(animation);
			
			animation.setElements(elementsList);
			animations.add(animation);
			
		}
		
	}
//	loop ends here
	videoTime += 4000;
	return videoTime;
	
}
	

}
