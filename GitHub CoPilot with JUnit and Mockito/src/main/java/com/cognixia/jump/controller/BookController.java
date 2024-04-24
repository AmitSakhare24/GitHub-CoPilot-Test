package com.cognixia.jump.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Book;
import com.cognixia.jump.service.BookService;


@RequestMapping("/api")
@RestController
public class BookController {
	
	@Autowired
	BookService service;
	

	@GetMapping("/book")
	public List<Book> getBooks() {
		return service.getAllBooks();
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBook(@PathVariable int id) throws ResourceNotFoundException {
		
		Book found = service.getBookById(id);
		
		return ResponseEntity.status(200).body(found);
	}
	
	@GetMapping("/book/author/amount/{author}")
	public ResponseEntity<?> getAmountOfBooksByAuthor(@PathVariable String author) {
		
		long amount = service.booksWritten(author);
		
		Map<String, String> info = new TreeMap<>();
		info.put("author", author);
		info.put("amountOfBooks", amount + "");
		
		return ResponseEntity.status(200).body(info);
	}
	
	@GetMapping("/book/search")
	public ResponseEntity<Book> findBook(@PathParam(value = "title") String title, @PathParam(value = "author") String author) throws ResourceNotFoundException {
		
		Book found = service.getBookByTitleAndAuthor(title, author);
		
		return ResponseEntity.status(200).body(found); 
	}
	
	@PostMapping("/book")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		
		Book created = service.createBook(book);
		
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/book")
	public ResponseEntity<Book> updateBook(@RequestBody Book book) throws ResourceNotFoundException {
		
		Book updated = service.updateBook(book);
		
		return ResponseEntity.status(200).body(updated);
	}
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable int id) throws ResourceNotFoundException {
		
		Book deleted = service.deleteBook(id);
		
		return ResponseEntity.status(200).body(deleted);
	}
	
}


