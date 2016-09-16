package nz.ac.auckland.library.domain;

import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

	@XmlElement(name="first_name")
	private String _firstname;
	
	@XmlElement(name="last_name")
	private String _lastname;
	
	public Person() {}
	
	public Person(String firstname, String lastname) {
		_firstname = firstname;
		_lastname = lastname;
	}
	
	public String getFirstname() {
		return _firstname;
	}
	
	public String getLastname() {
		return _lastname;
	}
}
