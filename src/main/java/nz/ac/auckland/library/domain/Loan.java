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
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Loan {
	
	@XmlElement(name="borrower")
	@OneToOne(cascade=CascadeType.PERSIST)
	private Member _borrower;
	
	@XmlElement(name="loan_date")
	@Temporal(TemporalType.DATE)
	private Date _loanDate;
	
	@XmlElement(name="return_date")
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
		this._borrower = borrower;
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
