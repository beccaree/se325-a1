package nz.ac.auckland.library.services;

import java.net.URI;

import javax.persistence.EntityManager;
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
	
	public LibraryResource() {}
	
	@POST
	@Consumes("application/xml")
	public Response addBook(nz.ac.auckland.library.dto.Book dtoBook) {
		EntityManager entityManager = PersistenceManager.instance().createEntityManager();
		_logger.debug("In @POST");
		
		try {
			entityManager.getTransaction().begin();
			
			_logger.debug("Read book: " + dtoBook);
			Book book = BookMapper.toDomainModel(dtoBook);
			entityManager.persist(book);
			
			entityManager.getTransaction().commit();
			_logger.debug("Created book: " + book);
			
			// Return a Response that specifies a status code of 201 Created along
			// with the Location header set to URI of the newly created Book.
			return Response.created(URI.create("/books/" + book.getId()))
					.build();
		} catch (Exception e) {
			
		} finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return null;
		
	}
	
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
	
}
