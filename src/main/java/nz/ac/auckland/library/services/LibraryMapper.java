package nz.ac.auckland.library.services;

import nz.ac.auckland.library.domain.Book;
import nz.ac.auckland.library.domain.Loan;
import nz.ac.auckland.library.domain.Member;

/**
 * Helper class to convert between dto Book and domain Book
 * @author Rebecca Lee (rlee291)
 *
 */
public class LibraryMapper {

	static Book toDomainModel(nz.ac.auckland.library.dto.Book dtoBook) {
		Book domainBook = new Book(
				dtoBook.getId(),
				dtoBook.getTitle(),
				dtoBook.getSubtitle(),
				dtoBook.getAuthor(),
				dtoBook.getGenre(),
				dtoBook.getPublisher(),
				dtoBook.getDatePublished());
		return domainBook;
	}
	
	static nz.ac.auckland.library.dto.Book toDto(Book book) {
		nz.ac.auckland.library.dto.Book dtoBook = 
				new nz.ac.auckland.library.dto.Book(
						book.getId(),
						book.getTitle(),
						book.getSubtitle(),
						book.getAuthor(),
						book.getGenre(),
						book.getPublisher(),
						book.getDatePublished());
		return dtoBook;
		
	}
	
	static Member toDomainModel(nz.ac.auckland.library.dto.Member dtoMember) {
		Member domainMember = new Member(
				dtoMember.getId(),
				dtoMember.getFirstname(),
				dtoMember.getLastname());
		return domainMember;
	}
	
	static nz.ac.auckland.library.dto.Member toDto(Member member) {
		nz.ac.auckland.library.dto.Member dtoMember = 
				new nz.ac.auckland.library.dto.Member(
						member.getId(),
						member.getFirstname(),
						member.getLastname());
		return dtoMember;
		
	}
	
	static Loan toDomainModel(nz.ac.auckland.library.dto.Loan dtoLoan) {
		Loan domainLoan = new Loan(
				dtoLoan.getBorrower(),
				dtoLoan.getLoanDate(),
				dtoLoan.getReturnDate());
		return domainLoan;
	}
	
	static nz.ac.auckland.library.dto.Loan toDto(Loan loan) {
		nz.ac.auckland.library.dto.Loan dtoLoan = 
				new nz.ac.auckland.library.dto.Loan(
						loan.getBorrower(),
						loan.getLoanDate(),
						loan.getReturnDate());
		return dtoLoan;
		
	}
	
}
