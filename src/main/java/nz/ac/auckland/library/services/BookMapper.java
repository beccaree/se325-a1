package nz.ac.auckland.library.services;

import nz.ac.auckland.library.domain.Book;

public class BookMapper {

	static Book toDomainModel(nz.ac.auckland.library.dto.Book dtoBook) {
		Book domainBook = new Book(dtoBook.getId(),
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
	
}
