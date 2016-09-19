package nz.ac.auckland.library.domain;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class to represent a Loan made by a Library member
 * 
 * @author Rebecca Lee (rlee291)
 *
 */
@Embeddable
public class Loan {

	@OneToOne
	@JoinColumn(name="member_id")
	private Member _borrower;

	@Temporal(TemporalType.DATE)
	private Date _loanDate;

	@Temporal(TemporalType.DATE)
	private Date _returnDate;
	
	public Loan(Member borrower, Date start, Date end) {
		_borrower = borrower;
		_loanDate = start;
		_returnDate = end;
	}
	
	public Loan() {}

	public Member getBorrower() {
		return _borrower;
	}

	public void setBorrower(Member borrower) {
		_borrower = borrower;
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
