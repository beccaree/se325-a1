package nz.ac.auckland.library.domain;

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
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

	@XmlAttribute(name="id")
	private long _id;
	
	@XmlElement(name="first_name")
	private String _firstname;
	
	@XmlElement(name="last_name")
	private String _lastname;
	
	@XmlElement(name="date_of_birth")
	private LocalDate _dateOfBirth;
	
}
