package nz.ac.auckland.library.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Class to represent a request made by a Member of the library
 * represented by member, book title and author
 * 
 * @author Rebecca Lee (rlee291)
 *
 */
@Entity
public class Request {

	@Id
	@GeneratedValue
	@XmlAttribute(name="id")
	private Long _id;
	
	@XmlElement(name="made_by")
	private Member _madeBy;
	
	@XmlElement(name="book_title")
	private String _bookTitle;
	
	@XmlElement(name="author_first")
	private String _authorFirst;
	
	@XmlElement(name="author_last")
	private String _authorLast;
	
	public Request(Member member, String title, String firstname, String lastname) {
		_madeBy = member;
		_bookTitle = title;
		_authorFirst = firstname;
		_authorLast = lastname;
	}
	
	public Long getId() {
		return _id;
	}
	
	public Member getRequestBy() {
		return _madeBy;
	}
	
	public String getBookTitle() {
		return _bookTitle;
	}
	
	public String getAuthorFirst() {
		return _authorFirst;
	}
	
	public String getAuthorLast() {
		return _authorLast;
	}
}
