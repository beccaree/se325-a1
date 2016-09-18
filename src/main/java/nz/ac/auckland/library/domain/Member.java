package nz.ac.auckland.library.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class represents a person who is a member of the library web service
 * Child class of Person with extra fields id and current books they hold
 * @author Rebecca Lee (rlee291)
 *
 */
@Entity
public class Member extends Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@XmlAttribute(name="member_id")
	private long _id;
	
	@XmlElement(name="current_books")
	@OneToMany(fetch=FetchType.LAZY)
	private Set<Book> _currentBooks = new HashSet<Book>();

	public Member(long id, String firstname, String lastname) {
		super(firstname, lastname);
		_id = id;
	}
	
	protected Member() {
		super();
	}
	
	public long getId() {
		return _id;
	}
	
	public void setId(long id) {
		_id = id;
	}
	
	public Set<Book> getCurrentlyHeldBooks() {
		return _currentBooks;
	}

	public void addToCurrentBooks(Book book) {
		this._currentBooks.add(book);
	}

}
