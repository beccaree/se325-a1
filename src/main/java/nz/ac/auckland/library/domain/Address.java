package nz.ac.auckland.library.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Class to represent the an address
 * @author user
 *
 */
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

	public String getStreetNumber() {
		return _streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this._streetNumber = streetNumber;
	}

	public String getStreetName() {
		return _streetName;
	}

	public void setStreetName(String streetName) {
		this._streetName = streetName;
	}

	public String getSuburb() {
		return _suburb;
	}

	public void setSuburb(String suburb) {
		this._suburb = suburb;
	}

	public String getCity() {
		return _city;
	}

	public void setCity(String city) {
		this._city = city;
	}

	public String getZipCode() {
		return _zipCode;
	}

	public void setZipCode(String zipCode) {
		this._zipCode = zipCode;
	}

}
