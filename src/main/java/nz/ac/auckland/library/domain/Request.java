package nz.ac.auckland.library.domain;

import javax.persistence.Entity;

/**
 * 
 * @author user
 *
 */
@Entity
public class Request {

	private Long _id;
	
	private Member _madeBy;
	
	private String _bookTitle;
	
	private String _authorFirst;
	
	private String _authorLast;
	
	public Request(Member member, String title, String firstname, String lastname) {
		_madeBy = member;
		_bookTitle = title;
		_authorFirst = firstname;
		_authorLast = lastname;
	}
	
	public Long getId() {
		return _id;
	}
	
	public Member getRequestBy() {
		return _madeBy;
	}
	
	public String getBookTitle() {
		return _bookTitle;
	}
	
	public String getAuthorFirst() {
		return _authorFirst;
	}
	
	public String getAuthorLast() {
		return _authorLast;
	}
}
