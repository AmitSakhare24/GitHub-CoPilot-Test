package com.cognixia.jump.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min = 1, max=100)
	@Column(nullable = false)
	private String title;
	
	// first and last name start with capital letters and separated by single space
	@Pattern(regexp = "^[A-Z][a-z]* [A-Z][a-z]*$")
	@Column(nullable = false)
	private String author;

	private LocalDate published;
	
	
	public Book() {
		
	}

	public Book(Integer id, @Size(min = 1) String title, @Pattern(regexp = "^[A-Z][a-z]* [A-Z][a-z]*$") String author,
			LocalDate published) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.published = published;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDate getPublished() {
		return published;
	}

	public void setPublished(LocalDate published) {
		this.published = published;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", published=" + published + "]";
	}
	
	public String toJson() {
		
		return "{\"id\" : " + id
				+ ", \"title\" : \"" + title + "\""
				+ ", \"author\" : \"" + author + "\""
				+ ", \"published\" : \"" + published + "\"}";
		
	}

}
