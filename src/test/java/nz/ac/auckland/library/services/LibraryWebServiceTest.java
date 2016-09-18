package nz.ac.auckland.library.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nz.ac.auckland.library.domain.Author;
import nz.ac.auckland.library.domain.BookGenre;
import nz.ac.auckland.library.domain.Loan;
import nz.ac.auckland.library.dto.Member;
import nz.ac.auckland.library.dto.Book;

public class LibraryWebServiceTest {
	private static final String WEB_SERVICE_URI = "http://localhost:10000/services/books";
	
	private static final Logger _logger = LoggerFactory.getLogger(LibraryWebServiceTest.class);
	
	private static Client _client;
	
	/**
	 * One-time setup method that creates a Web service client.
	 */
	@BeforeClass
	public static void setUpClient() {
		_client = ClientBuilder.newClient();
	}
	
	/**
	 * One-time finalisation method that destroys the Web service client.
	 */
	@AfterClass
	public static void destroyClient() {
		_client.close();
	}
	
	/**
	 * Tests that the Web service can create, query and delete a Book.
	 * (This test is based off the given example in the dto-parolee project) 
	 */
	@Test
	public void addBook() {
		Author author = new Author("J.K.", "Rowling");
		Book hp = new Book("Harry Potter and the Deathly Hallows", "part 2", author, BookGenre.FICTION, "", new Date(2007-1900, 7-1, 21));
		
		Response response = _client
				.target(WEB_SERVICE_URI).request()
				.post(Entity.xml(hp));
		if (response.getStatus() != 201) {
			fail("Failed to add new book");
		}
		
		String location = response.getLocation().toString();
		response.close();
		
		// Query the Web service for the new Parolee
		Book hpFromLibrary = _client.target(location).request()
				.accept("application/xml").get(Book.class);
		
		// Check that the book returned is the same as the one added in the test
		assertEquals(hp.getTitle(), hpFromLibrary.getTitle());
		assertEquals(hp.getSubtitle(), hpFromLibrary.getSubtitle());
		assertEquals(hp.getAuthor().getFirstname(), hpFromLibrary.getAuthor().getFirstname());
		assertEquals(hp.getAuthor().getLastname(), hpFromLibrary.getAuthor().getLastname());
		assertEquals(hp.getDatePublished(), hpFromLibrary.getDatePublished());
		assertEquals(hp.getGenre(), hpFromLibrary.getGenre());
		assertEquals(hp.getPublisher(), hpFromLibrary.getPublisher()); // should be null
		
		response = _client.target(location).request().delete();
		if (response.getStatus() != 200) {
			fail("Failed to delete book");
		}
		response.close();
	}
	
	/**
	 * Tests that the web service can change a book's details
	 */
	@Test
	public void updateBook() {
		Book book = _client
				.target(WEB_SERVICE_URI + "/1").request()
				.accept("application/xml").get(Book.class);
		
		book.setSubtitle("part 1");
		
		Response response = _client
				.target(WEB_SERVICE_URI + "/1").request()
				.put(Entity.xml(book));
		if (response.getStatus() != 204) {
			fail("Failed to update Book");
		}
		response.close();
		
		Book book1 = _client
				.target(WEB_SERVICE_URI + "/1").request()
				.accept("application/xml").get(Book.class);
		// check that the subtitle has indeed been updated 
		assertEquals(book.getSubtitle(), book1.getSubtitle());
	}
	
	/**
	 * Tests that the Web service processes requests for all Books.
	 * - query parameters
	 * - HATEOAS
	 */
	@Test
	public void queryAllBooks() {
		List<Book> books = _client
				.target(WEB_SERVICE_URI + "?start=1").request()
				.accept("application/xml")
				.get(new GenericType<List<Book>>() {});
		if (books == null) {
			fail("Failed to retrieve books");
		} else {
			// dummy data has 2 books
			assertEquals(2, books.size());
		}
	}
	
	/**
	 * Tests that a web service can add and query loans for a book, includes
	 * - querying for a member
	 * - changing the availability of a book and recording the borrower
	 */
	@Test
	public void addLoan() {
		Member amy = _client
				.target(WEB_SERVICE_URI + "/members/3").request()
				.accept("application/xml").get(Member.class);
		
		Loan loan = new Loan(MemberMapper.toDomainModel(amy), new Date(2016-1900, 9-1, 3), null);
		// issue book with id=1 to Amy 
		Response response = _client
				.target(WEB_SERVICE_URI + "/1/issue_book").request()
				.post(Entity.xml(loan));
		if (response.getStatus() != 204) {
			fail("Failed to add a loan to book " + response.getStatus());
		}
		
		// check that amy has 2 books in her current books (one from dummy data)
		List<Book> books = _client
				.target(WEB_SERVICE_URI + "?mid=3").request()
				.accept("application/xml")
				.get(new GenericType<List<Book>>() {});
//		assertEquals(2, books.size());
	}
	
	/**
	 * Tests retrieving the loan history of a book 
	 */
	@Test
	public void getLoans() {
		
	}
	
	/**
	 * Test Requesting a book
	 */
	
	/**
	 * Test returning a book
	 */
	
}
