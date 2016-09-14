package nz.ac.auckland.library.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.joda.time.LocalDate;

/**
 * Class representing a person
 * @author Rebecca Lee (rlee291)
 *
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

	@Id
	@GeneratedValue
	@XmlAttribute(name="id")
	private long _id;
	
	@XmlElement(name="first_name")
	private String _firstname;
	
	@XmlElement(name="last_name")
	private String _lastname;
	
	@XmlElement(name="date_of_birth")
	private LocalDate _dateOfBirth;
	
	public Person(String firstname, String lastname) {
		_firstname = firstname;
		_lastname = lastname;
	}
	
	public Person() {}

	public String getLastname() {
		return _lastname;
	}
		
	public String getFirstname() {
		return _firstname;
	}
	
	public LocalDate getDateOfBirth() {
		return _dateOfBirth;
	}

	public void setDateOfBirth(LocalDate date) {
		this._dateOfBirth = date;
	}
	
}
