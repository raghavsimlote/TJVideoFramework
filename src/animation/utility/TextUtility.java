package animation.utility;

import java.util.Vector;

import com.traveljar.vo.FriendVO;

public class TextUtility {
	
	public static String getIconPath(String type) {
		
		if ( type.equals("car") ) {
			return "resources/images/icons/sedan2.png";
		} else if ( type.equals("city") ) {
			return "resources/images/icons/city8.png";
		}
		
		return null;
		
	}
	
	public static String getEmotIconPath(String type) {
		
		if ( type.equals("happy") ) {
			return "resources/images/icons/sedan2.png";
		} else if ( type.equals("sad") ) {
			return "resources/images/icons/city8.png";
		}
		
		return null;
		
	}
	
	public static String getImagePathFromFriendId(Vector<FriendVO> friendsVector, String name) {
		
		for (int i=0; i<friendsVector.size(); i++) {
			FriendVO friend = (FriendVO) friendsVector.get(i);
			if (friend.getName().equals(name)) {
				return friend.getProfileImagePath();
			}
		}
		
		return null;
	}
	
}
