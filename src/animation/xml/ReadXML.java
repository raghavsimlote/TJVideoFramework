package animation.xml;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import animation.model.Animation;
import animation.model.Animations;

/**
 *
 * @author norrye
 */
public class ReadXML {

    Animations animations;

    public ReadXML() {
        JAXBContext jc;
        try {
            jc = JAXBContext.newInstance(Animations.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
//            File xml = new File("src/animations.xml");
            File xml = new File("src/animation/traveljar/jaipur_jodhpur_trip.xml");
            animations = (Animations) unmarshaller.unmarshal(xml);
            
//           arrange second set of animations for people introduction
//            TextImageUtility.arrangeImageTextAnimations(animations.getAnimations());
            
            
        } catch (JAXBException ex) {
            Logger.getLogger(ReadXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Animation> getAnimations() {
        return animations.getAnimations();
    }

    public static void main(String[] args) {
        JAXBContext jc;
        try {
            jc = JAXBContext.newInstance(Animations.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
//            File xml = new File("src/animations.xml");
            File xml = new File("src/animation/traveljar/jaipur_jodhpur_trip.xml");
            Animations animations = (Animations) unmarshaller.unmarshal(xml);
        } catch (JAXBException ex) {
            Logger.getLogger(ReadXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}