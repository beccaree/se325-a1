package nz.ac.auckland.library.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;

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
	
	@OneToMany(fetch=FetchType.EAGER)
	@Column(unique=false)
	private List<Book> _currentBooks = new ArrayList<Book>();

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
	
	public List<Book> getCurrentlyHeldBooks() {
		return _currentBooks;
	}

	public void addToCurrentBooks(Book book) {
		this._currentBooks.add(book);
	}

}
