package nz.ac.auckland.library.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nz.ac.auckland.library.domain.Author;
import nz.ac.auckland.library.domain.Availability;
import nz.ac.auckland.library.domain.Book;
import nz.ac.auckland.library.domain.BookGenre;
import nz.ac.auckland.library.domain.Loan;
import nz.ac.auckland.library.domain.Member;

/**
 * Web service resource implementation for the Library application. An instance
 * of this class handles all HTTP requests for the Library Web service.
 * 
 * @author Rebecca Lee (rlee291)
 *
 */
@Path("/books")
public class LibraryResource {
	private static final Logger _logger = LoggerFactory.getLogger(LibraryResource.class);
	
	private List<AsyncResponse> responses = new ArrayList<AsyncResponse>();
	
	public LibraryResource() {
		populateDatabase();
	}

	/**
	 * Adds a new Book to the library system. The new book is described by a 
	 * nz.ac.auckland.library.dto.Book object.
	 * 
	 * @param dtoBook - the Book details included in the HTTP request body.
	 */
	@POST
	@Consumes("application/xml")
	public Response addBook(nz.ac.auckland.library.dto.Book dtoBook) {
		EntityManager em = PersistenceManager.instance().createEntityManager();
		
		try {
			em.getTransaction().begin();
			
			_logger.debug("Read book: " + dtoBook);
			Book book = LibraryMapper.toDomainModel(dtoBook);
			em.persist(book);
			
			em.getTransaction().commit();
			_logger.debug("Created book: " + book);
			
			// Return a Response that specifies a status code of 201 Created along
			// with the Location header set to URI of the newly created Book.
			return Response.created(URI.create("/books/" + book.getId()))
					.build();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return null;
	}
	
	/**
	 *  Returns a Book with a particular id. The returned Book is represented by a
	 * nz.ac.auckland.library.dto.Book object.
	 * 
	 * @param id is the unique identifier of the Book.
	 * 
	 */
	@GET
	@Path("{id}")
	public Response getBook(@PathParam("id")long id) {
		EntityManager em = PersistenceManager.instance().createEntityManager();
		try {
			em.getTransaction().begin();
			_logger.debug("Retrieving Book: id = " + id);
			Book book = em.find(Book.class, id);
			if (book == null) {
				return Response.status(404).build(); // not found
			}
			em.getTransaction().commit();
			
			return Response.ok(LibraryMapper.toDto(book)).build();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {        
			if(em != null && em.isOpen()){
				em.close();
	       	}
		}
		return null;
	}
	
	/**
	 * Updates the details of a book
	 * @param id is the unique identifier of the book
	 * @param dtoBook is representation of book containing the changes made
	 */
	@PUT
	@Path("{id}")
	@Consumes("application/xml")
	public void updateBook(@PathParam("id")long id, nz.ac.auckland.library.dto.Book dtoBook) {
		EntityManager em = PersistenceManager.instance().createEntityManager();
		try {
			em.getTransaction().begin();
			_logger.debug("Retrieving Book: id = " + id);
			Book book = em.find(Book.class, id);
			if (book == null) {
				throw new NotFoundException("Book id = " + id + " does not exist"); // not found
			}
			
			// set new values
			book.setAuthor(dtoBook.getAuthor());
			book.setDatePublished(dtoBook.getDatePublished());
			book.setGenre(dtoBook.getGenre());
			book.setId(dtoBook.getId());
			book.setPublisher(dtoBook.getPublisher());
			book.setSubtitle(dtoBook.getSubtitle());
			book.setTitle(dtoBook.getTitle());
			
			// persist changes to the database
			em.persist(book);
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {        
			if(em != null && em.isOpen()){
				em.close();
	       	}
		}
	}
	
	/**
	 * Delete a book from the database
	 */
	@DELETE
	@Path("{id}")
	public Response deleteBook(@PathParam("id")long id) {
		EntityManager em = PersistenceManager.instance().createEntityManager();
		try {
			Book book = em.find(Book.class, id);
			if (book == null) {
				throw new NotFoundException("Book id = " + id + " does not exist");
			}
			
			em.getTransaction().begin();
			em.remove(book);
			em.getTransaction().commit();
			
			return Response.ok().build();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {        
			if(em != null && em.isOpen()){
				em.close();
	       	}
		}
		return null;
	}
	
	/**
	 * Returns a List of nz.ac.auckland.library.dto.Book objects, according to the 
	 * query parameters in the URI
	 * 
	 */
	@GET
	@Produces("application/xml")
	public Response getBooks(@DefaultValue("0") @QueryParam("mid")long mid, 
			@DefaultValue("1") @QueryParam("start")int start, 
			@DefaultValue("0") @QueryParam("size")int size, 
			@Context UriInfo uriInfo) {
		URI uri = uriInfo.getAbsolutePath();		
		Link previous = null;
		Link next = null;
		
		if(size != 0 && start > 1) { // if size is 0, retrieve everything
			// there are previous Book - create a previous link
			_logger.debug("Making PREV link");
			previous = Link.fromUri(uri + "?mid={mid}start={start}&size={size}")
					.rel("prev")
					.build(start - 1, size);
		}
		
		// retrieve the list of books
		EntityManager em = PersistenceManager.instance().createEntityManager();
		List<Book> books = new ArrayList<Book>();
		try {
			
			if (mid == 0) { // query all books
				em.getTransaction().begin();
				_logger.debug("Querying all books mid = " + mid + "start, size = " + start + "," + size);
				books = em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
				em.getTransaction().commit();
			} else { // query member's current books
				em.getTransaction().begin();
				_logger.debug("Querying books held by member id: " + mid);
				Member member = em.find(Member.class, mid);
				if (member == null) {
					throw new NotFoundException("Member id = " + mid + " does not exist");
				}
				books = member.getCurrentlyHeldBooks();
				em.getTransaction().commit();
			}
	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {        
			if(em != null && em.isOpen()){
				em.close();
	       	}
		}
		// trim list of books according to query parameters and convert to dto objects
		List<nz.ac.auckland.library.dto.Book> dtoBooks = new ArrayList<nz.ac.auckland.library.dto.Book>();
		int upperBound = 0;
		if (size == 0) {
			upperBound = books.size();
		}else {
			upperBound = start + size - 1;
		}
		for(int i = start-1; i < upperBound; i++) {
			dtoBooks.add(LibraryMapper.toDto(books.get(i)));
		}
		
		if(size != 0 && start + size <= books.size()) { // there are successive books - create a next link
			_logger.info("Making NEXT link");
			next = Link.fromUri(uri + "?start={start}&size={size}")
					.rel("next")
					.build(start + 1, size);
		}
		GenericEntity<List<nz.ac.auckland.library.dto.Book>> entity = 
				new GenericEntity<List<nz.ac.auckland.library.dto.Book>>(dtoBooks) {};
		
		// build a response 
		ResponseBuilder builder = Response.ok(entity);
 		if(previous != null) {
 			builder.links(previous);
 		}
 		if(next != null) {
 			builder.links(next);
 		}
 		return builder.build();
	}
	
	/**
	 * Add a new member to the database
	 */
	@POST
	@Path("members")
	@Consumes("application/xml")
	public Response addMember(nz.ac.auckland.library.dto.Member dtoMember) {
		EntityManager em = PersistenceManager.instance().createEntityManager();
		
		try {
			em.getTransaction().begin();
			
			_logger.debug("Read Member: " + dtoMember);
			Member member = LibraryMapper.toDomainModel(dtoMember);
			em.persist(member);
			
			em.getTransaction().commit();
			_logger.debug("Created member: " + member);
			
			// Return a Response that specifies a status code of 201 Created along
			// with the Location header set to URI of the newly created Book.
			return Response.created(URI.create("/books/members/" + member.getId()))
					.build();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return null;
	}
	
	/**
	 * Returns the member with unique identifier id
	 */
	@GET
	@Path("members/{id}")
	@Produces("application/xml")
	public nz.ac.auckland.library.dto.Member getMember(@PathParam("id")long id) {
		EntityManager em = PersistenceManager.instance().createEntityManager();
		try {
			em.getTransaction().begin();
			_logger.debug("Retrieving member: id = " + id);
			Member member = em.find(Member.class, id);
			if (member == null) {
				throw new NotFoundException("Member id = " + id + " does not exist");
			}
			
			em.getTransaction().commit();
			return LibraryMapper.toDto(member);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {        
			if(em != null && em.isOpen()){
				em.close();
	       	}
		}
		return null;
	}
	
	/**
	 * Issue a book identified by id to the member in the loan
	 * - sets the availability of the book to false
	 */
	@POST
	@Path("{id}/issue_book")
	@Consumes("application/xml")
	public void issueBookToMember(@PathParam("id")long id, @CookieParam("userid")long userid,
			nz.ac.auckland.library.dto.Loan dtoLoan) {
		EntityManager em = PersistenceManager.instance().createEntityManager();
		try {
			em.getTransaction().begin();
			// add current loan to book without return date, and change book's availability
			_logger.debug("Retrieving Book: id = " + id);
			Book book = em.find(Book.class, id);
			if (book == null) {
				throw new NotFoundException("Book id = " + id + " does not exist");
			}
			_logger.debug("Retrieving Member: id = " + userid);
			Member member = em.find(Member.class, userid);
			if (member == null) {
				throw new NotFoundException("Member id = " + userid + " does not exist");
			}
			Loan loan = LibraryMapper.toDomainModel(dtoLoan);
			loan.setBorrower(member);
			book.addLoan(loan);
			book.setAvailability(new Availability(false, member));
			em.persist(book);
			
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {        
			if(em != null && em.isOpen()){
				em.close();
	       	}
		}
	}
	
	@GET
	@Path("{id}/loan_history")
	@Produces("application/xml")
	public Response getLoans(@PathParam("id")long id) {
		EntityManager em = PersistenceManager.instance().createEntityManager();
		Book book = null;
		try {
			em.getTransaction().begin();
			book = em.find(Book.class, id);
			if (book == null) {
				throw new NotFoundException("Book id = " + id + " does not exist");
			}
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {        
			if(em != null && em.isOpen()){
				em.close();
	       	}
		}
		List<nz.ac.auckland.library.dto.Loan> dtoLoans = new ArrayList<nz.ac.auckland.library.dto.Loan>();
		for (Loan loan : book.getLoanHistory()) {
			dtoLoans.add(LibraryMapper.toDto(loan));
		}
		
		GenericEntity<List<nz.ac.auckland.library.dto.Loan>> entity = new GenericEntity<List<nz.ac.auckland.library.dto.Loan>>(dtoLoans) {};
		return Response.ok(entity).build();
	}
	
//	@GET
//	@Path("request")
//	public void submitRequest(@QueryParam("bid")long bid,
//			@QueryParam("mid")long mid, @Suspended AsyncResponse response) { // SUBSCRIBE
//		// member mid subscribes to book bid
//		response
//	}
	
	private void populateDatabase() {
		EntityManager em = PersistenceManager.instance( ).createEntityManager( );
		try {
			em.getTransaction().begin();
			
			// create some books
			Book hp = new Book(0, "Harry Potter and the Deathly Hallows", "", new Author("J.K.", "Rowling"), BookGenre.FICTION, "", new Date(2007-1900, 7-1, 21));
			Book sj = new Book(0, "Steve Jobs", "", new Author("Walter", "Isaacson"), BookGenre.BIOGRAPHY, "Simon & Shuster (U.S.)", new Date(2011-1900, 10-1, 24));
			// create a member
			Member amy = new Member(0, "Amy", "Wright");
			// issue a book to a member
			sj.addLoan(new Loan(amy, new Date(2012-1900, 11-1, 25), new Date(2012-1900, 12-1, 25)));
			sj.addLoan(new Loan(amy, new Date(2014-1900, 10-1, 1), null));
			sj.setAvailability(new Availability(false, amy));
			amy.addToCurrentBooks(sj);
			
			em.persist(hp);
			em.persist(sj);
			em.persist(amy);
			em.getTransaction( ).commit( );
		} catch( Exception e ) {
			e.printStackTrace();
		} finally {        
			if( em != null && em.isOpen( ) ) {
				em.close( );
	       	}
		}	
	}
	
}
