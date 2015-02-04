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
			
			int startTime = 0;
			int animationCounter=0;
			
//			journey intro starts here, may be we should think of applying css
			JourneyVO journey = travelJar.getJourney();
			if ( journey != null ) {
				
				animationCounter++;
				animation = new Animation();
				animation.setStart(startTime);
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
				animation.setStart(startTime);
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
				startTime+=1000;
				animation.setStart(startTime);
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
				startTime+=1000;
				animation.setStart(startTime);
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
				startTime-=500;
				animation.setStart(startTime);
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
				
				startTime+=3000;
				
//				setting end time
				if ( animations.size()-animationCounter >= 0 ) {
					for ( int i=0; i<animations.size(); i++ ) {
						animation = (Animation) animations.get(i);
						animation.setEnd(startTime);
					}
				}
				
			}

			animationCounter = 0;
//			journey intro ends here
			Vector friendsVector = travelJar.getFriendsVector();
			if ( friendsVector != null ) {
				
				if ( friendsVector.size()>0 ) {
					
//					for 4 maximum in one row
					if ( friendsVector.size() <=4 ) {
						
						int xGap = (int)( ( Data.width - ( 60*friendsVector.size() ) ) / ( friendsVector.size()+1) );
						animationCounter++;
						animation = new Animation();
						animation.setStart(startTime);
						startTime+=2000;
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
							animation.setStart( startTime );
							startTime += 2000;
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
							element.setStartPosition("-0.1,0.55");
							element.setEndPosition("0.1,0.55");
							element.setSpeedX(45);
							element.setSpeedY(0);
							element.setEffect("boxMiddle;" + xCoordinate + ";");
							element.setColor("black");
							elementsList.add(element);
							animation.setElements(elementsList);
							animations.add(animation);
							
						}
						
					}
//					may continue and change row	
					
					
					
//					setting end time
					if ( animations.size()-animationCounter >= 0 ) {
						for ( int i=animations.size()-animationCounter; i<animations.size(); i++ ) {
							animation = (Animation) animations.get(i);
							animation.setEnd(startTime);
						}
					}
					
					
				}
			}
			
//			gray color slide animation starts here
			animation = new Animation();
			animation.setName("slide");
			animation.setStart(startTime);
			startTime += 500;
			animation.setEnd(startTime);
			animation.setBackground("gray");
			animations.add(animation);
//			slide animation ends here
			
//			gray color slide animation starts here
			animation = new Animation();
			animation.setName("slide");
			animation.setStart(startTime);
			startTime += 500;
			animation.setEnd(startTime);
			animation.setBackground("white");
			animations.add(animation);
//			slide animation ends here
			
			
//			people intro starts here
			Vector milestonesVector = travelJar.getMilestonesVector();
			if ( milestonesVector != null ) {
			
				if ( ( milestonesVector.size()>1 ) && ( milestonesVector.size()<=4 ) ) {
					
					int pictureTimeGap = startTime;
					Vector<Animation> stoneAnimations = new Vector<Animation>();
					Vector<Animation> carAnimations =  new Vector<Animation>();

					int totalWidth = 0;
					
					Animation milestonesAnimation = new Animation();
					milestonesAnimation.setStart(pictureTimeGap);
					milestonesAnimation.setEnd(35000);
					milestonesAnimation.setBackground("white");
					elementsList = new ArrayList<Element>();
					element = new Element();
					element.setType("text");
					element.setDescription("Milestones Title");
					element.setHeight(50);
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
//						pictureTimeGap += 5000;
						stoneAnimation.setStart(pictureTimeGap);
						System.out.println("Picture Gap Time: " + pictureTimeGap);
						stoneAnimation.setEnd(35000);
						elementsList = new ArrayList<Element>();
						element = new Element();
						element.setType("image");
						String iconPathString = TextUtility.getIconPath(stone.getType());
						System.out.println("Text path: " + iconPathString);
						element.setDescription(iconPathString);
						element.setWidth(64);
						element.setHeight(64);
						element.setStartPosition("0.0,0.4");
						element.setEndPosition("0.1,0.4");
						element.setSpeedX(20);
						element.setSpeedY(0);
						element.setEffect("");
//						element.setEffect("fade;0-0.1-0.2-0.3-0.4-0.5-0.6-0.7-0.8-0.9-1");
						element.setColor("white");
						elementsList.add(element);
						
						element = new Element();
						element.setType("text");
						element.setDescription(stone.getName());
						element.setHeight(20);
						
//						count the total width
						Text label = new Text(stone.getName());
						label.setFont(Font.font(null, FontWeight.SEMI_BOLD, element.getHeight()));
						int width  =  (int) label.getLayoutBounds().getWidth();
						element.setWidth(width);
						totalWidth += label.getLayoutBounds().getWidth();
//						System.out.println("Width is " + totalWidth);
						
						element.setStartPosition("0.0,0.55");
						element.setEndPosition("0.1,0.55");
						element.setSpeedX(20);
						element.setSpeedY(0);
						element.setEffect("");
//						element.setEffect("fade;0-0.1-0.2-0.3-0.4-0.5-0.6-0.7-0.8-0.9-1");
						element.setColor("black");
						elementsList.add(element);
						stoneAnimation.setElements(elementsList);
						animations.add(stoneAnimation);
						
//						add to the vector for future reference
						stoneAnimations.add(stoneAnimation);
						
						Vector<PictureVO> picturesVector = stone.getPictures();
						if ( picturesVector != null ) {
							
							if ( picturesVector.size()>0 ) {
								for ( int j=0; j<picturesVector.size(); j++) {
									PictureVO picture = (PictureVO) picturesVector.get(j);
									if ( picture!= null ) {
										
										animation = new Animation();
										pictureTimeGap += 2000;
										animation.setStart(pictureTimeGap );
										animation.setEnd( pictureTimeGap + 2000 );
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
										element.setWidth(Data.width/2);
										element.setHeight(Data.height/2);
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
										element.setEffect("leftBelowImage;" + (Data.width/2) + ";" + (Data.height/2) + ";30;" );
										element.setColor("white");
										elementsList.add(element);
										
										element = new Element();
										element.setType("text");
										element.setDescription(picture.getPlace());
										element.setHeight(25);
										element.setStartPosition("0.0,0.9");
										element.setEndPosition("0.0,0.9");
										element.setSpeedX(0);
										element.setSpeedY(0);
										element.setEffect("rightBelowImage;" + (Data.width/2) + ";" + (Data.height/2) + ";30;" );
										element.setColor("white");
										elementsList.add(element);
										animation.setElements(elementsList);
										animations.add(animation);
										
									}
									
								}
								
							}
//							inner loop ends here
							pictureTimeGap += 2000;
							
						}
						
						if ( ( (i+1)<milestonesVector.size() ) ) {
							
							Animation carAnimation = new Animation();
							carAnimation.setBackground("white");
							carAnimation.setStart(pictureTimeGap);
							pictureTimeGap += 2000;
							carAnimation.setEnd(pictureTimeGap);
							elementsList = new ArrayList<Element>();
							element = new Element();
							element.setType("image");
							String carIconPathString = TextUtility.getIconPath("car");
							element.setDescription( carIconPathString );
							element.setWidth(64);
							element.setHeight(64);
							element.setEffect("");
//							element.setEffect("Lmove;0.17-0.2-0.5-0.6-0.8-0.9-1-1.001-1");
							element.setStartPosition("0.00,0.45");
							element.setEndPosition("0.00,0.45");
							element.setSpeedX(30);
							element.setSpeedY(0);
							element.setColor("white");
							element.setClip(false);
							elementsList.add(element);
							
//							element = new Element();
//							element.setType("path");
//							element.setDescription( "footsteps.png" );
//							element.setWidth(20);
//							element.setHeight(20);
//							element.setEffect("feet;0");
//							element.setCurrentPosition("0.00,0.45");
//							element.setStartPosition("0.00,0.45");
//							element.setEndPosition("0.00,0.45");
//							element.setSpeedX(5);
//							element.setSpeedY(0);
//							element.setColor("white");
//							element.setClip(false);
//							elementsList.add(element);
							
							
							
							carAnimation.setElements(elementsList);
							animations.add(carAnimation);
							carAnimations.add(carAnimation);
							
							
						}
//						car image with path
						
					}
//					outer loop ends here
					pictureTimeGap += 2000;
					milestonesAnimation.setEnd(pictureTimeGap);
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
						
						stoneAnimation.setEnd(pictureTimeGap);
					
						
						if ( stoneAnimations.size() > 1 ) {
							
							if ( i==0 ) {
								
								Animation carAnimation = (Animation) carAnimations.get(i);
								elementsList = carAnimation.getElements();
								carAnimation.setEnd(pictureTimeGap);
								element = elementsList.get(0);
								element.setEffect(element.getEffect() + ";VehicleXStartPosition;" + ( xPos + width )  + ";");
								
//								element = elementsList.get(1);
//								element.setEffect(element.getEffect() + ";VehicleXStartPosition;" + ( xPos + width )  + ";");
								
							} else if ( ( (i+1) < stoneAnimations.size() ) ) {
								
								Animation carAnimation = (Animation) carAnimations.get(i-1);
								elementsList = carAnimation.getElements();
								carAnimation.setEnd(pictureTimeGap);
								element = elementsList.get(0);
								element.setEffect(element.getEffect() + ";VehicleXStopPosition;" + ( xPos - element.getWidth() )  + ";");
								
//								element = elementsList.get(1);
//								element.setEffect(element.getEffect() + ";VehicleXStopPosition;" + ( xPos - element.getWidth() )  + ";");
								
								carAnimation = (Animation) carAnimations.get(i);
								elementsList = carAnimation.getElements();
								carAnimation.setEnd(pictureTimeGap);
								element = elementsList.get(0);
								element.setEffect(element.getEffect() + ";VehicleXStartPosition;" + ( xPos + width )  + ";");
								
//								element = elementsList.get(1);
//								element.setEffect(element.getEffect() + ";VehicleXStartPosition;" + ( xPos + width )  + ";");
								
							} else if ( ( (i+1) == stoneAnimations.size() ) ) {
								
								Animation carAnimation = (Animation) carAnimations.get(i-1);
								elementsList = carAnimation.getElements();
								element = elementsList.get(0);
								element.setEffect(element.getEffect() + ";VehicleXStopPosition;" + ( xPos - element.getWidth() )  + ";");
								
//								element = elementsList.get(1);
//								element.setEffect(element.getEffect() + ";VehicleXStopPosition;" + ( xPos - element.getWidth() )  + ";");
							}
								
						}
						
					}
					
					
					
//					set end time of stone animations same as milestone time
					
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
