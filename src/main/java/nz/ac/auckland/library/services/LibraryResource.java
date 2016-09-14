package nz.ac.auckland.library.services;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nz.ac.auckland.library.domain.Book;

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

	private Map<Long, Book> _libraryDB;
	
	public LibraryResource() {}
	
//	@GET
//	public MyEntity myServiceMethod( ) {
//		EntityManager entityMgr = PersistenceManager.instance( ).createEntityManager( );         
//		try {             
//			entityMgr.getTransaction( ).begin( );
//			// Do useful work.
//			entityMgr.getTransaction( ).commit( );         
//		} catch( Exception e ) {
//			// Handle/propagate any exceptions.
//	       	} finally {             
//	       	if( entityMgr != null && entityMgr.isOpen( ) ) {                
//	       		entityMgr.close( );             
//	       	}         
//	    }     
//	}
	
	
	@POST
	@Consumes("application/xml")
	public Response createBook(Book book) {
		EntityManager entityMgr = PersistenceManager.instance().createEntityManager();
		
		try {
			EntityTransaction tx = entityMgr.getTransaction();
			tx.begin();
			//_logger.debug("Read book: " + dtoParolee);
			// Do stuff			
			
			tx.commit();
		} catch (Exception e) {
			
		} finally {
			if (entityMgr != null && entityMgr.isOpen()) {
				entityMgr.close();
			}
		}
		return null;
		
	}
	
}
