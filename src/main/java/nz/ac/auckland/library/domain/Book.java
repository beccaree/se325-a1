package nz.ac.auckland.library.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.joda.time.LocalDate;

import nz.ac.auckland.library.jaxb.LocalDateAdapter;

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
	@GeneratedValue
	private long _id;
	private String _title;
	private String _subtitle;
	private Person _author;
	private BookGenre _genre;
	private String _publisher;
	private LocalDate _datePublished;
	private Availability _availablility = new Availability();
	private List<Loan> _loanHistory = new ArrayList<Loan>();
	
	public Book(String title, String subtitle, Person author, BookGenre genre, String publisher, LocalDate datePublished) {
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

	public String getTitle() {
		return _title;
	}

	public String getSubtitle() {
		return _subtitle;
	}

	public void setSubtitle(String subtitle) {
		_subtitle = subtitle;
	}

	public Person getAuthor() {
		return _author;
	}

	public void setAuthor(Person author) {
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

	public LocalDate getDatePublished() {
		return _datePublished;
	}

	public void setDatePublished(LocalDate datePublished) {
		_datePublished = datePublished;
	}

	public Availability getAvailablility() {
		return _availablility;
	}

	public void setAvailablility(Boolean isAvailable) {
		_availablility.setAvailable(isAvailable);
	}

	public List<Loan> getLoanHistory() {
		return _loanHistory;
	}

	public void addToLoanHistory(Loan loan) {
		_loanHistory.add(loan);
	}

}
