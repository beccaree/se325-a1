package nz.ac.auckland.library.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import nz.ac.auckland.library.domain.Author;
import nz.ac.auckland.library.domain.BookGenre;

/**
 * The data transfer object representation of a Book, see domain.Book for more info
 * @author Rebecca Lee (rlee291)
 *
 */
@XmlRootElement(name="book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

	@XmlAttribute(name="id")
	private long _id;
	
	@XmlElement(name="title")
	private String _title;
	
	@XmlElement(name="subtitle")
	private String _subtitle;
	
	@XmlElement(name="author")
	private Author _author;
	
	@XmlElement(name="genre")
	private BookGenre _genre;
	
	@XmlElement(name="publisher")
	private String _publisher;
	
	@XmlElement(name="date_published")
	private Date _datePublished;
	
//	@XmlElement(name="availability")
//	private Availability _availablility = new Availability();
//	
//	@XmlElement(name="loan_history")
//	private List<Loan> _loanHistory = new ArrayList<Loan>();
	
	protected Book() {}
	
	public Book(String title, String subtitle, Author author, BookGenre genre, String publisher, Date datePublished) {
		 this(0, title, subtitle, author, genre, publisher, datePublished);
	}
	
	public Book(long id, String title, String subtitle, Author author, BookGenre genre, String publisher, Date datePublished) {
		_id = id;
		_title = title;
		_subtitle = subtitle;
		_author = author;
		_genre = genre;
		_publisher = publisher;
		_datePublished = datePublished;
	}

	public long getId() {
		return _id;
	}

	public String getTitle() {
		return _title;
	}

	public String getSubtitle() {
		return _subtitle;
	}

	public void setSubtitle(String subtitle) {
		_subtitle = subtitle;
	}

	public Author getAuthor() {
		return _author;
	}

	public void setAuthor(Author author) {
		_author = author;
	}

	public BookGenre getGenre() {
		return _genre;
	}

	public void setGenre(BookGenre genre) {
		_genre = genre;
	}

	public String getPublisher() {
		return _publisher;
	}

	public void setPublisher(String publisher) {
		_publisher = publisher;
	}

	public Date getDatePublished() {
		return _datePublished;
	}

	public void setDatePublished(Date datePublished) {
		_datePublished = datePublished;
	}

//	public Availability getAvailablility() {
//		return _availablility;
//	}
//
//	public void setAvailablility(Boolean isAvailable) {
//		_availablility.setAvailable(isAvailable);
//	}
//
//	public List<Loan> getLoanHistory() {
//		return _loanHistory;
//	}
//
//	public void addToLoanHistory(Loan loan) {
//		_loanHistory.add(loan);
//	}
	
}
