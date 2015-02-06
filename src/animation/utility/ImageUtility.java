package animation.utility;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import animation.model.Element;

public class ImageUtility {
	
//	set resize image accordingly
	public static void resizeImage( Image image, Element element) {
		
		double givenWidth = element.getWidth();
		double givenHeight = element.getHeight();
		LogUtility.printConsole("Given Width: " + givenWidth + " Given Height: " + givenHeight);
		
		double oldWidth = image.getWidth();
		double oldHeight = image.getHeight();
		LogUtility.printConsole("Old Width: " + oldWidth + " Old Height: " + oldHeight);
		
		double scaleFactor = (oldWidth > oldHeight) ? givenWidth / oldWidth : givenHeight / oldHeight;
		LogUtility.printConsole("Scale Factor: " + scaleFactor + " Scaled Width: " +  (oldWidth * scaleFactor) + " Scaled Height: " + (oldHeight * scaleFactor));
		
		element.setWidth((int) (oldWidth * scaleFactor));
		element.setHeight((int)(oldHeight * scaleFactor));
			
	}
	
//	set resize image accordingly, currently also displays facebook effect
	public static void resizeImageToFitFullScreen( Image image, Element element) {
		
		double givenWidth = element.getWidth();
		double givenHeight = element.getHeight();
		LogUtility.printConsole("Given Width: " + givenWidth + " Given Height: " + givenHeight);
		
		double oldWidth = image.getWidth();
		double oldHeight = image.getHeight();
		LogUtility.printConsole("Old Width: " + oldWidth + " Old Height: " + oldHeight);
		
		double scaleFactor = (oldWidth > oldHeight) ? givenWidth / oldWidth : givenHeight / oldHeight;
		LogUtility.printConsole("Scale Factor: " + scaleFactor + " Scaled Width: " +  (oldWidth * scaleFactor) + " Scaled Height: " + (oldHeight * scaleFactor));
		
		double newWidth = oldWidth * scaleFactor;
		double newHeight = oldHeight * scaleFactor;
		
		if ( newWidth < givenWidth ) {
			
			double ratio = (givenWidth / newWidth);
			newHeight = newHeight * ratio;
			newWidth = newWidth * ratio;
			
		}
		
		if ( newHeight < givenHeight ) {
			
			double ratio = (givenHeight / newHeight);
			newHeight = newHeight * ratio;
			newWidth = newWidth * ratio;
			
		}
				
		element.setWidth((int) (newWidth));
		element.setHeight((int)(newHeight));
		
		final ImageView selectedImage = new ImageView();    
        selectedImage.setImage(image);
        selectedImage.setFitWidth(newWidth);
        selectedImage.setFitHeight(newHeight);
        Pane pane = new Pane(selectedImage);
        Scene offScreenScene = new Scene(pane);
        WritableImage croppedImage = selectedImage.snapshot(null, null);
        image = croppedImage;
		element.setWidth((int) (croppedImage.getWidth()));
		element.setHeight((int)(croppedImage.getHeight()));
	}
	
}