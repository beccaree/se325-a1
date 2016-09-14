package nz.ac.auckland.library.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
	
	@XmlElement(name="street_number")
	private String _streetNumber;
	
	@XmlElement(name="street_name")
	private String _streetName;
	
	@XmlElement(name="suburb")
	private String _suburb;
	
	@XmlElement(name="city")
	private String _city;
	
	@XmlElement(name="zip_code")
	private String _zipCode;

}
