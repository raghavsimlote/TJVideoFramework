package animation.utility;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import animation.model.Animation;
import animation.model.Element;

public class TextImageUtility {
	
	
	public static void arrangeImageTextAnimations (List <Animation> animationsList) {
		
         Animation currentAnimation = (Animation) animationsList.get(3);
         List <Element> elementsList = currentAnimation.getElements();
         Element firstElement =  (Element) elementsList.get(0);
         Element secondElement =  (Element) elementsList.get(1);
         
         currentAnimation = (Animation) animationsList.get(4);
         elementsList = currentAnimation.getElements();
         Element thirdElement =  (Element) elementsList.get(0);
         Element fourthElement =  (Element) elementsList.get(1);
         
         currentAnimation = (Animation) animationsList.get(5);
         elementsList = currentAnimation.getElements();
         Element fifthElement =  (Element) elementsList.get(0);
         Element sixthElement =  (Element) elementsList.get(1);
         
         
         Image image = new Image(firstElement.getDescription());
         
         Text label = new Text( secondElement.getDescription() );
         label.setFont(Font.font(null, FontWeight.SEMI_BOLD, secondElement.getHeight()));
         
         
         
	}
	
}