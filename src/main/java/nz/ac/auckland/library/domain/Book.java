package nz.ac.auckland.library.domain;

import java.util.List;

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
 * - isAvailable: if the book is currently available;
 * - loanHistory: the book's history of loans;
 * 
 * A Book is uniquely identified by an id value of type Long.
 * 
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
	private Person _author;
	
	@XmlElement(name="genre")
	private BookGenre _genre;
	
	@XmlElement(name="publisher")
	private Publisher _publisher;
	
	@XmlElement(name="date_published")
	@XmlJavaTypeAdapter(value=LocalDateAdapter.class)
	private LocalDate _datePublished;
	
	@XmlElement(name="is_available")
	private Boolean _isAvailable;
	
	@XmlElement(name="loan_history")
	private List<Loan> _loanHistory;
	
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
		this._subtitle = subtitle;
	}

	public Person getAuthor() {
		return _author;
	}

	public void setAuthor(Person author) {
		this._author = author;
	}

	public BookGenre getGenre() {
		return _genre;
	}

	public void setGenre(BookGenre genre) {
		this._genre = genre;
	}

	public Publisher getPublisher() {
		return _publisher;
	}

	public void setPublisher(Publisher publisher) {
		this._publisher = publisher;
	}

	public LocalDate getDatePublished() {
		return _datePublished;
	}

	public void setDatePublished(LocalDate datePublished) {
		this._datePublished = datePublished;
	}

	public Boolean getIsAvailable() {
		return _isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this._isAvailable = isAvailable;
	}

	public List<Loan> getLoanHistory() {
		return _loanHistory;
	}

	public void setLoanHistory(List<Loan> loanHistory) {
		this._loanHistory = loanHistory;
	}

}
