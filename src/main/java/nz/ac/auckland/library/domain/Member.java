package nz.ac.auckland.library.domain;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Class represents a person who is a member of the library web service
 * @author Rebecca Lee (rlee291)
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Member extends Person {
	
	@XmlElement(name="currently_held_books")
	private Set<Book> currentlyHeldBooks;

}
