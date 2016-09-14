package nz.ac.auckland.library.domain;

import javax.xml.bind.annotation.XmlElement;

/**
 * Class represents the availability of a book in the library
 * @author Rebecca Lee (rlee291)
 *
 */
public class Availability {

	@XmlElement(name="is_available")
	private Boolean _isAvailable = true;
	
	@XmlElement(name="holder")
	private Member _holder;
	
	public Availability() {} // default is available, and no holder
	
	public void setAvailable(Boolean isAvailable) {
		_isAvailable = isAvailable;
	}

	public Member getHolder() {
		return _holder;
	}
	
	public void setHolder(Member holder) {
		_holder = holder;
	}
}
