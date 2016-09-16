package nz.ac.auckland.library.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;

/**
 * Class represents the availability of a book in the library
 * @author Rebecca Lee (rlee291)
 *
 */
@Embeddable
public class Availability {

	@XmlElement(name="is_available")
	private Boolean _isAvailable = true;
	
	@XmlElement(name="holder")
	@OneToOne(cascade=CascadeType.PERSIST)
	private Member _holder;
	
	public Availability() {} // default is available, and no holder
	
	public void setAvailable(Boolean isAvailable) {
		_isAvailable = isAvailable;
	}

	public Member getHolder() {
		return _holder;
	}
	
	public void setHolder(Member member) {
		_holder = member;
	}
}
