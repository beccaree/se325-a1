package nz.ac.auckland.library.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Class representing a Publisher for a Book, with fields name and address
 * 
 * @author Rebecca Lee (rlee291)
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Publisher {
	
	@XmlElement(name="name")
	private String _name;
	
	@XmlElement(name="address")
	private Address _address;

}
