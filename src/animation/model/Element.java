/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animation.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author norrye
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Element {

    @XmlElement
    private String name;
    @XmlElement
    private String type;
    @XmlElement
    private String description;
    @XmlElement
    private int width;
    @XmlElement
    private int height;
    @XmlElement
    private boolean clip;
    @XmlElement
    private String color;
    @XmlElement
    private String effect;
    @XmlElement
    private String startPosition;
    @XmlElement
    private String endPosition;
    @XmlElement
    private String currentPosition;
    @XmlElement
    private double speedX;
    @XmlElement
    private double speedY;
    
    boolean changed = false;

	public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public boolean isClip() {
        return clip;
    }

    public void setStartPosition(String startPosition) {
        this.startPosition = startPosition;
    }

    public void setEndPosition(String endPosition) {
        this.endPosition = endPosition;
    }

    
    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getStartPosition() {
        return startPosition;
    }

    public String getEndPosition() {
        return endPosition;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setSpeedX(double speedX) {
        if (!false) {
            this.speedX = speedX;
        }
    }

    public void setSpeedY(double speedY) {
        if (!false) {
            this.speedY = speedY;
        }
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

	public void setType(String type) {
		this.type = type;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setClip(boolean clip) {
		this.clip = clip;
	}
    
    

}