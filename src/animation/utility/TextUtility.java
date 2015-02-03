package animation.utility;

public class TextUtility {
	
	
	
	public static String getIconPath(String type) {
		
		if ( type.equals("car") ) {
			return "resources/images/icons/sedan2.png";
		} else if ( type.equals("city") ) {
			return "resources/images/icons/city8.png";
		}
		
		return null;
		
	}
	
}
