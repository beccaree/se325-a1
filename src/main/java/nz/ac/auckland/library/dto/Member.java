package nz.ac.auckland.library.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class to represent a data tranfer object for a member
 * For more info, see domain.Member class
 * @author Rebecca (rlee291)
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Member {

	@XmlAttribute(name="member_id")
	private long _id;

	@XmlElement(name="first_name")
	private String _firstname;
	
	@XmlElement(name="last_name")
	private String _lastname;
	
	public Member(long id, String firstname, String lastname) {
		_id = id;
		_firstname = firstname;
		_lastname = lastname;
	}
	
	protected Member() {}
	
	public long getId() {
		return _id;
	}
	
	public void setId(long id) {
		_id = id;
	}
	
	public String getFirstname() {
		return _firstname;
	}
	
	public void setFirstname(String name) {
		_firstname = name;
	}
	
	public String getLastname() {
		return _lastname;
	}
	
	public void setLastname(String name) {
		_lastname = name;
	}
}
