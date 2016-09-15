package nz.ac.auckland.library.domain;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Embeddable
@XmlAccessorType(XmlAccessType.FIELD)
public class Author extends Person {
	
	public Author(String firstname, String lastname) {
		super(firstname, lastname);
	}
	
	protected Author() {
		super();
	}

}
