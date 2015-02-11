package com.traveljar.vo;

import java.util.Vector;

import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

public class StoneVO {
	
	public StoneVO(JSONObject stonesJson) {
		if ( stonesJson != null ) {
			this.setName(stonesJson.optString("name"));
			this.setType(stonesJson.optString("type"));
			try {
				JSONObject picturesJson =  stonesJson.getJSONObject("pictures");
				if ( picturesJson != null ) {
					JSONArray picturesArray = picturesJson.getJSONArray("picture");
					if ( picturesArray!=null && picturesArray.length()>0 ) {
						Vector<PictureVO> picturesVector = new Vector<PictureVO>();
						for (int i=0; i<picturesArray.length(); i++) {
							JSONObject pictureObject = (JSONObject) picturesArray.getJSONObject(i);
							PictureVO picture = new PictureVO(pictureObject);
							picturesVector.add(picture);
						}
						this.setPictures(picturesVector);
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				JSONObject videosJson =  stonesJson.getJSONObject("videos");
				if ( videosJson != null ) {
					JSONArray videosArray = videosJson.getJSONArray("video");
					if ( videosArray!=null && videosArray.length()>0 ) {
						Vector<VideoVO> videosVector = new Vector<VideoVO>();
						for (int i=0; i<videosArray.length(); i++) {
							JSONObject videoObject = (JSONObject) videosArray.getJSONObject(i);
							VideoVO video = new VideoVO(videoObject);
							videosVector.add(video);
						}
						this.setVideos(videosVector);
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				JSONObject notesJson =  stonesJson.getJSONObject("notes");
				if ( notesJson != null ) {
					JSONArray notesArray = notesJson.getJSONArray("note");
					if ( notesArray!=null && notesArray.length()>0 ) {
						Vector<NoteVO> notesVector = new Vector<NoteVO>();
						for (int i=0; i<notesArray.length(); i++) {
							JSONObject noteObject = (JSONObject) notesArray.getJSONObject(i);
							NoteVO note = new NoteVO(noteObject);
							notesVector.add(note);
						}
						this.setNotes(notesVector);
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				JSONObject moodsJson =  stonesJson.getJSONObject("moods");
				if ( moodsJson != null ) {
					JSONArray moodsArray = moodsJson.getJSONArray("mood");
					if ( moodsArray!=null && moodsArray.length()>0 ) {
						Vector<MoodVO> moodsVector = new Vector<MoodVO>();
						for (int i=0; i<moodsArray.length(); i++) {
							JSONObject moodObject = (JSONObject) moodsArray.getJSONObject(i);
							MoodVO mood = new MoodVO(moodObject);
							moodsVector.add(mood);
						}
						this.setMoods(moodsVector);
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	private String name;
	private String type;
	private Vector <PictureVO> pictures;
	private Vector <VideoVO> videos;
	private Vector <NoteVO> notes;
	private Vector <MoodVO> moods;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Vector<PictureVO> getPictures() {
		return pictures;
	}
	public void setPictures(Vector<PictureVO> pictures) {
		this.pictures = pictures;
	}
	public Vector<VideoVO> getVideos() {
		return videos;
	}
	public void setVideos(Vector<VideoVO> videos) {
		this.videos = videos;
	}
	public Vector<NoteVO> getNotes() {
		return notes;
	}
	public void setNotes(Vector<NoteVO> notes) {
		this.notes = notes;
	}
	public Vector<MoodVO> getMoods() {
		return moods;
	}
	public void setMoods(Vector<MoodVO> moods) {
		this.moods = moods;
	}
	
}
