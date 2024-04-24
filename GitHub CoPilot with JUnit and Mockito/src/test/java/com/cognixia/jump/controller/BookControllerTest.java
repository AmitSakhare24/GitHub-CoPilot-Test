package com.cognixia.jump.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Book;
import com.cognixia.jump.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {
	
	private static final String STARTING_URI = "http://localhost:8080/api";

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private BookService service;
	
	@InjectMocks
	private BookController controller;
	
	
	@Test
	public void testGetBooks() throws Exception {
		
		String uri = STARTING_URI + "/book";
		
		List<Book> books = new ArrayList<>();
		
		books.add( new Book(1, "Test Book 1", "Foo Bar", LocalDate.of(2013, 2, 4) ) );
		books.add( new Book(2, "Test Book 2", "Foo Bar", LocalDate.of(2019, 10, 22) ) );
		
		when( service.getAllBooks() ).thenReturn(books);
		
		mvc.perform( get(uri) )
				.andDo( print() )
				.andExpect( status().isOk() )
				.andExpect( content().contentType( MediaType.APPLICATION_JSON_VALUE ) )
				.andExpect( jsonPath( "$.length()" ).value( books.size() ) )
				.andExpect( jsonPath( "$[0].id" ).value( books.get(0).getId() ) )
				.andExpect( jsonPath( "$[0].title" ).value( books.get(0).getTitle() ) )
				.andExpect( jsonPath( "$[0].author" ).value( books.get(0).getAuthor() ) )
				.andExpect( jsonPath( "$[0].published" ).value( books.get(0).getPublished().toString() ) )
				.andExpect( jsonPath( "$[1].id" ).value( books.get(1).getId() ) )
				.andExpect( jsonPath( "$[1].title" ).value( books.get(1).getTitle() ) )
				.andExpect( jsonPath( "$[1].author" ).value( books.get(1).getAuthor() ) )
				.andExpect( jsonPath( "$[1].published" ).value( books.get(1).getPublished().toString() ) );
		
		verify( service, times(1) ).getAllBooks();
		verifyNoMoreInteractions( service );
	}
	
	@Test
	public void testGetBook() throws Exception {
		
		int id = 1;
		String uri = STARTING_URI + "/book/{id}";
		
		Book book = new Book(id, "Test Book 1", "Foo Bar", LocalDate.now());
		
		when( service.getBookById(id) ).thenReturn(book);
		
		mvc.perform( get(uri, id) )
				.andDo( print() )
				.andExpect( status().isOk() )
				.andExpect( content().contentType( MediaType.APPLICATION_JSON_VALUE ) )
				.andExpect( jsonPath( "$.id" ).value( book.getId() ) )
				.andExpect( jsonPath( "$.title" ).value( book.getTitle()) )
				.andExpect( jsonPath( "$.author" ).value( book.getAuthor() ) )
				.andExpect( jsonPath( "$.published" ).value( book.getPublished().toString() ) );
		
		verify( service, times(1) ).getBookById(id);
		verifyNoMoreInteractions(service);
	}
	
	@Test
	public void testGetBookNotFound() throws Exception {
		
		int id = 1;
		String uri = STARTING_URI + "/book/{id}";
		
		when( service.getBookById(id) )
			.thenThrow( new ResourceNotFoundException("Book", id) );
		
		mvc.perform( get(uri, id) )
			.andDo( print() )
			.andExpect( status().isNotFound() );
		
		verify( service, times(1) ).getBookById(id);
		verifyNoMoreInteractions(service);
	}
	
	@Test
	public void testGetAmountOfBooksByAuthor() throws Exception {
		
		String author = "Foo Bar";
		long amount = 2;
		String uri = STARTING_URI + "/book/author/amount/{author}";
		
		when( service.booksWritten(author) ).thenReturn(amount);
		
		mvc.perform( get(uri, author) )
			.andDo( print() )
			.andExpect( status().isOk() )
			.andExpect( content().contentType( MediaType.APPLICATION_JSON_VALUE ) )
			.andExpect( jsonPath( "$.author" ).value( author ) )
			.andExpect( jsonPath( "$.amountOfBooks" ).value( amount ) );
		
		verify( service, times(1) ).booksWritten(author);
		verifyNoMoreInteractions(service);
	}
	
	@Test
	public void testFindBook() throws Exception {
		
		String title = "Test";
		String author = "Test";
		Book book = new Book(1, title, author, LocalDate.now());
		
		String uri = STARTING_URI + "/book/search/?title=" + title + "&author=" + author;
		
		when( service.getBookByTitleAndAuthor(title, author) ).thenReturn(book);
		
		mvc.perform( get(uri) )
			.andDo( print() )
			.andExpect( status().isOk() )
			.andExpect( content().contentType( MediaType.APPLICATION_JSON_VALUE ) )
			.andExpect( jsonPath( "$.title" ).value( title ) )
			.andExpect( jsonPath( "$.author" ).value( author ) );
		
		verify( service, times(1) ).getBookByTitleAndAuthor(title, author);
		verifyNoMoreInteractions(service);
	}
	
	@Test
	public void testFindBookNotFound() throws Exception {
		
		String title = "Test";
		String author = "Test";
		
		String uri = STARTING_URI + "/book/search/?title=" + title + "&author=" + author;
		
		when( service.getBookByTitleAndAuthor(title, author) )
			.thenThrow(new ResourceNotFoundException("Book with title = " + title + " and author = " + author + " was not found"));
		
		mvc.perform( get(uri) )
			.andDo( print() )
			.andExpect( status().isNotFound() );
		
		verify( service, times(1) ).getBookByTitleAndAuthor(title, author);
		verifyNoMoreInteractions(service);
	}
	
	@Test
	public void testCreateBook() throws Exception {
		
		String uri = STARTING_URI + "/book";
		
		Book book = new Book(1, "Test Book 1", "Foo Bar", LocalDate.now());
		
		when( service.createBook( Mockito.any(Book.class) ) ).thenReturn(book);
		
		mvc.perform( post(uri) 
					 .content( book.toJson() )
					 .contentType( MediaType.APPLICATION_JSON_VALUE ) )
			.andDo( print() )
			.andExpect( status().isCreated() )
			.andExpect( content().contentType( MediaType.APPLICATION_JSON_VALUE ) );
		
		verify(service, times(1)).createBook(Mockito.any(Book.class));
		verifyNoMoreInteractions(service);
	}
	
	@Test
	public void testUpdateBook() throws Exception {
		
		String uri = STARTING_URI + "/book";
		
		Book book = new Book(1, "Test Book 1", "Foo Bar", LocalDate.now());
		
		when( service.updateBook( Mockito.any(Book.class) ) ).thenReturn(book);
		
		mvc.perform( put(uri) 
					 .contentType(MediaType.APPLICATION_JSON_VALUE)
					 .content( book.toJson() ) )
			.andExpect( status().isOk() );
		
		verify( service, times(1) ).updateBook( Mockito.any(Book.class) );
		verifyNoMoreInteractions(service);
	}
	
	@Test
	public void testUpdateBookNotFound() throws Exception {
	
		String uri = STARTING_URI + "/book";
		int id = 1;
		
		Book book = new Book(id, "Test Book 1", "Foo Bar", LocalDate.now());
		
		when(service.updateBook( Mockito.any(Book.class) ) )
				.thenThrow(new ResourceNotFoundException("Book", id) );
		
		mvc.perform( put(uri) 
				 .content( book.toJson() )
				 .contentType( MediaType.APPLICATION_JSON_VALUE ) )
			.andDo( print() )
			.andExpect( status().isNotFound() );
		
		verify(service, times(1)).updateBook(Mockito.any(Book.class));
		verifyNoMoreInteractions(service);
	}
	
	@Test
	public void testDeleteBook() throws Exception {
		
		String uri = STARTING_URI + "/book/{id}";
		int id = 1;
		
		Book book = new Book(id, "Test Book 1", "Foo Bar", LocalDate.now());
		
		when(service.deleteBook(id)).thenReturn(book);
		
		mvc.perform( delete(uri, id) )
				.andDo( print() )
				.andExpect( status().isOk() );
		
		verify(service, times(1)).deleteBook(id);
		verifyNoMoreInteractions(service);
	}
	
	@Test
	public void testDeleteBookNotFound() throws Exception {
		
		String uri = STARTING_URI + "/book/{id}";
		int id = 1;
		
		when(service.deleteBook(id))
			.thenThrow( new ResourceNotFoundException("Book", id) );
		
		mvc.perform( delete(uri, id) )
				.andDo( print() )
				.andExpect( status().isNotFound() );
		
		verify( service, times(1) ).deleteBook(id);
		verifyNoMoreInteractions(service);
	}

	// converts any object to a JSON string
	public static String asJsonString(final Object obj) {

		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

}





