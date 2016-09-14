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
	
	public Publisher(String name, Address address) {
		_name = name;
		_address = address;
	}
	
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public Address getAddress() {
		return _address;
	}

	public void setAddress(Address address) {
		this._address = address;
	}	

}
