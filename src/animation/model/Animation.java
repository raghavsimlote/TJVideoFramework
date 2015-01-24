package animation.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author norrye
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Animation {

    @XmlElement
    private String name;
    @XmlElement(name = "element")
    private List<Element> elements;
    @XmlElement
    private double start;
    @XmlElement
    private double end;
    @XmlElement
    private String background;
    
    public String getName() {
        return name;
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }

    public String getBackground() {
        return background;
    }

    public List<Element> getElements() {
        if (elements == null) {
            elements = new ArrayList<>();
        }
        return elements;
    }

	public void setName(String name) {
		this.name = name;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	public void setStart(double start) {
		this.start = start;
	}

	public void setEnd(double end) {
		this.end = end;
	}

	public void setBackground(String background) {
		this.background = background;
	}
    
    

}