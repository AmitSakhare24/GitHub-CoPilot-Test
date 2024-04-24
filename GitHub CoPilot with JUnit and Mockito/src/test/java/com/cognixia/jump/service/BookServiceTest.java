package com.cognixia.jump.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognixia.jump.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	
	
	@Mock
	private BookRepository repo;

	@InjectMocks
	private BookService service;
	
	
	
	// test that the getAllBooks() method, make sure it returns expected value
	@Test
	public void testGetAllBooks() throws Exception {
		
	}
	
	// test that the getBookById() method, make sure it returns expected value
	@Test
	public void testGetBookById() throws Exception {
		
	}
	
	// test that the getBookById() method, test if exception is thrown when book not found
	@Test
	public void testGetBookByIdNotFound() throws Exception {
		
	}
	
	// test that the booksWritten() method, make sure it returns expected value
	@Test
	public void testBooksWritten() throws Exception {
		
	}
	
	// test that the getBookByTitleAndAuthor() method, make sure it returns expected value
	@Test
	public void testGetBookByTitleAndAuthor() throws Exception {
		
	}
	
	// test that the getBookByTitleAndAuthor() method, test if exception is thrown when book not found
	@Test
	public void testGetBookByTitleAndAuthorNotFound() throws Exception {
		
	}
	
	// test that the createBook() method, make sure it returns expected value
	@Test
	public void testCreateBook() throws Exception {
		
	}
	
	// test that the updateBook() method, make sure it returns expected value
	@Test
	public void testUpdateBook() throws Exception {
		
	}
	
	// test that the updateBook() method, test if exception is thrown when book not found
	@Test
	public void testUpdateBookNotFound() throws Exception {
		
	}
	
	// test that the deleteBook() method, make sure it returns expected value
	@Test
	public void testDeleteBook() throws Exception {
		
	}
	
	// test that the deleteBook() method, test if exception is thrown when book not found
	@Test
	public void testDeleteBookNotFound() throws Exception {
		
	}
	
}




