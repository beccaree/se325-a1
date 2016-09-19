package nz.ac.auckland.library.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import nz.ac.auckland.library.domain.Member;

/**
 * data transfer object representation of a loan made by a member
 * @author Rebecca Lee (rlee291)
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Loan {

	@XmlElement(name="borrower")
	private Member _borrower;
	
	@XmlElement(name="loan_date")
	private Date _loanDate;
	
	@XmlElement(name="return_date")
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
