package com.cognixia.jump.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	// returns amount of rows with the given author
	public long countByAuthor(String author);
	
	// find the exact book with given title and author
	public Optional<Book> findByTitleAndAuthor(String title, String author);
}
