package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Book;
import com.cognixia.jump.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository repo;
	
	public List<Book> getAllBooks() {
		return repo.findAll();
	}
	
	public Book getBookById(int id) throws ResourceNotFoundException {
		
		Optional<Book> found = repo.findById(id);
		
		if(found.isEmpty()) {
			throw new ResourceNotFoundException("Book", id);
		}
		
		return found.get();
	}
	
	public long booksWritten(String author) {
		
		long amount = repo.countByAuthor(author);
		
		return amount;
	}
	
	public Book getBookByTitleAndAuthor(String title, String author) throws ResourceNotFoundException {
		
		Optional<Book> found = repo.findByTitleAndAuthor(title, author);
		
		if(found.isEmpty()) {
			throw new ResourceNotFoundException("Book with title = " + title + " and author = " + author + " was not found");
		}
		
		return found.get();
	}
	
	public Book createBook(Book book) {
		
		book.setId(null);
		
		Book created = repo.save(book);
		
		return created;
	}
	
	public Book updateBook(Book book) throws ResourceNotFoundException {
		
		if( repo.existsById( book.getId() ) ) {
			Book updated = repo.save(book);
			
			return updated;
		}
		
		throw new ResourceNotFoundException("Book", book.getId());
	}

	public Book deleteBook(int id) throws ResourceNotFoundException {
		
		Book deleted = getBookById(id);
		
		repo.deleteById(id);
		
		return deleted;	
	}
	
}












