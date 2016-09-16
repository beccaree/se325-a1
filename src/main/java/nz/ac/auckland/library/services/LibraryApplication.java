package nz.ac.auckland.library.services;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import nz.ac.auckland.library.services.LibraryResource;

/**
 * Application subclass for the Library Web service
 * (parolee-dto as template)
 *
 * @author Rebecca Lee (rlee291)
 *
 */
@ApplicationPath("/services")
public class LibraryApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();
	
	public LibraryApplication() {
		// Register the BlogResource singleton to handle HTTP requests.
	    singletons.add(new LibraryResource());
	    singletons.add(PersistenceManager.instance());
	      
	    // Register the ContextResolver class for JAXB.
	    
	}
	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}
	
}
