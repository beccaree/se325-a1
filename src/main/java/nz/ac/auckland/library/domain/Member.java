package nz.ac.auckland.library.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Class represents a person who is a member of the library web service
 * @author Rebecca Lee (rlee291)
 *
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Member extends Person {
	
	@Column(unique=true)
	private String _username;
	
	@XmlElement(name="currently_held_books")
	private Set<Book> currentlyHeldBooks = new HashSet<Book>();
	
	public Member(String username, String firstname, String lastname) {
		super(firstname, lastname);
		_username = username;
	}
	
	public Member(String username) {
		this(username, null, null);
	}
	
	protected Member() {}
	
	public String getUsername() {
		return _username;
	}

}
