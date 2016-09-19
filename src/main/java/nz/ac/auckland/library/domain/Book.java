package nz.ac.auckland.library.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class to represent a Book in the library, a book has the following fields:
 * 
 * - Details: title, subtitle, author, book type, date published, genre;
 * - Availablility: if the book is currently available, and if not who it is held by;
 * - loanHistory: the book's history of loans;
 * 
 * A Book is uniquely identified by an id value of type Long.
 * 
 * @author Rebecca Lee (rlee291)
 *
 */
@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long _id;
	private String _title;
	private String _subtitle;
	private Author _author;
	
	@Enumerated(EnumType.STRING)
	private BookGenre _genre;
	private String _publisher;
	
	@Temporal(TemporalType.DATE)
	private Date _datePublished;
	private Availability _availablility = new Availability();
	
	@ElementCollection(fetch=FetchType.EAGER)
	private List<Loan> _loanHistory = new ArrayList<Loan>();
	
	public Book(long id, String title, String subtitle, Author author, BookGenre genre, String publisher, Date datePublished) {
		_id = id;
		_title = title;
		_subtitle = subtitle;
		_author = author;
		_genre = genre;
		_publisher = publisher;
		_datePublished = datePublished;
	}
	
	public Book() {}

	public long getId() {
		return _id;
	}
	
	public void setId(long id) {
		_id = id;
	}

	public String getTitle() {
		return _title;
	}
	
	public void setTitle(String title) {
		_title = title;
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

	public Availability getAvailablility() {
		return _availablility;
	}

	public void setAvailability(Availability availability) {
		_availablility = availability;
	}

	public List<Loan> getLoanHistory() {
		return _loanHistory;
	}

	public void addLoan(Loan loan) {
		_loanHistory.add(loan);
	}

}
