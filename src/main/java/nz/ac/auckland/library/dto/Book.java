package nz.ac.auckland.library.dto;

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

import nz.ac.auckland.library.domain.Availability;
import nz.ac.auckland.library.domain.BookGenre;
import nz.ac.auckland.library.domain.Loan;
import nz.ac.auckland.library.domain.Person;
import nz.ac.auckland.library.jaxb.LocalDateAdapter;

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
	private Person _author;
	
	@XmlElement(name="genre")
	private BookGenre _genre;
	
	@XmlElement(name="publisher")
	private String _publisher;
	
	@XmlElement(name="date_published")
	@XmlJavaTypeAdapter(value=LocalDateAdapter.class)
	private LocalDate _datePublished;
	
	@XmlElement(name="availability")
	private Availability _availablility = new Availability();
	
	@XmlElement(name="loan_history")
	private List<Loan> _loanHistory = new ArrayList<Loan>();
	
	protected Book() {}
	
	public Book(String title, String subtitle, Person author, BookGenre genre, String publisher, LocalDate datePublished) {
		 this(0, title, subtitle, author, genre, publisher, datePublished);
	}
	
	public Book(long id, String title, String subtitle, Person author, BookGenre genre, String publisher, LocalDate datePublished) {
		_id = id;
		_title = title;
		_subtitle = subtitle;
		_author = author;
		_genre = genre;
		_publisher = publisher;
		_datePublished = datePublished;
	}
	
}
