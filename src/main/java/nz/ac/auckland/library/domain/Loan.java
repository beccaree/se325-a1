package nz.ac.auckland.library.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class to represent a Loan made by a Library member
 * 
 * @author Rebecca Lee (rlee291)
 *
 */
@Embeddable
public class Loan {

	private long _borrowerId;

	@Temporal(TemporalType.DATE)
	private Date _loanDate;

	@Temporal(TemporalType.DATE)
	private Date _returnDate;
	
	public Loan(long borrower, Date start, Date end) {
		_borrowerId = borrower;
		_loanDate = start;
		_returnDate = end;
	}
	
	public Loan() {}

	public long getBorrower() {
		return _borrowerId;
	}

	public void setBorrower(long borrower) {
		_borrowerId = borrower;
	}

	public Date getLoanDate() {
		return _loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this._loanDate = loanDate;
	}

	public Date getReturnDate() {
		return _returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this._returnDate = returnDate;
	}
	
}
