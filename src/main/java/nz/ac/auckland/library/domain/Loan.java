package nz.ac.auckland.library.domain;

import javax.xml.bind.annotation.XmlAccessType;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.joda.time.LocalDate;
/**
 * Class to represent a Loan made by a Library member
 * 
 * @author Rebecca Lee (rlee291)
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Loan {
	
	@XmlElement(name="borrower")
	private Member _borrower;
	
	@XmlElement(name="loan_date")
	private LocalDate _loanDate;
	
	@XmlElement(name="return_date")
	private LocalDate _returnDate;
	
	public Loan(Member borrower, LocalDate start, LocalDate end) {
		_borrower = borrower;
		_loanDate = start;
		_returnDate = end;
	}

	public Member getBorrower() {
		return _borrower;
	}

	public void setBorrower(Member borrower) {
		this._borrower = borrower;
	}

	public LocalDate getLoanDate() {
		return _loanDate;
	}

	public void setLoanDate(LocalDate loanDate) {
		this._loanDate = loanDate;
	}

	public LocalDate getReturnDate() {
		return _returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this._returnDate = returnDate;
	}
	
}
