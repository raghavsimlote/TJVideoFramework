package animation.model;


import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author norrye
 */
@XmlRootElement(name="animations")
@XmlAccessorType(XmlAccessType.FIELD)
public class Animations {

    @XmlElement(name="animation")
    private List<Animation> animations;

    public List<Animation> getAnimations() {
        return animations;
    }
    
    
}
