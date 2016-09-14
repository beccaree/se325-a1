package nz.ac.auckland.library.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.joda.time.LocalDate;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nz.ac.auckland.library.domain.Book;
import nz.ac.auckland.library.domain.BookGenre;
import nz.ac.auckland.library.domain.Person;

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
	 * Runs before each unit test to restore Web service database. This ensures
	 * that each test is independent; each test runs on a Web service that has
	 * been initialised with a common set of Books.
	 */
	@Before
	public void reloadServerData() {
		Response response = _client
				.target(WEB_SERVICE_URI).request()
				.put(null);
		response.close();
	}
	
	/**
	 * One-time finalisation method that destroys the Web service client.
	 */
	@AfterClass
	public static void destroyClient() {
		_client.close();
	}
	
	/**
	 * Tests that the Web service can create a new Book.
	 * (This test is based off the given example in the dto-parolee project) 
	 */
	@Test
	public void addBook() {
		Person author = new Person("J.K.", "Rowling");
		Book hp = new Book("Harry Potter and the Philosopher's stone", author, BookGenre.FICTION, null, new LocalDate(1997, 6, 26));
		
		Response response = _client
				.target(WEB_SERVICE_URI).request()
				.post(Entity.xml(hp));
		if (response.getStatus() != 201) {
			fail("Failed to create new Book");
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
		assertEquals(hp.getDatePublished(), hpFromLibrary.getDatePublished());
		assertEquals(hp.getGenre(), hpFromLibrary.getGenre());
		assertEquals(hp.getPublisher(), hpFromLibrary.getPublisher()); // should be null
	}
	
	// test edit book
	
	// test getting books by particular author
	
	// test adding a loan (includes changing availability and borrower)
	
	// test checking availability
	
	// test currently held books by member
	
	// test requesting a book
	
}
