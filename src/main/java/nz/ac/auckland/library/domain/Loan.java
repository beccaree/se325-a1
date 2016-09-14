package nz.ac.auckland.library.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.joda.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class Loan {
	
	@XmlElement(name="borrower")
	private Member _borrower;
	
	@XmlElement(name="loan_date")
	private LocalDate _loanDate;
	
	@XmlElement(name="return_date")
	private LocalDate _returnDate;
	
}
