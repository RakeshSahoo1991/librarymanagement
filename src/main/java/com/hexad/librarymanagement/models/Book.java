/**
 * 
 */
package com.hexad.librarymanagement.models;

/**
 * @author srake
 *
 */
public class Book {
	
	private Integer bookId;
	private String name;
	private String author;
	private Integer bookCount;
	
	
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getBookCount() {
		return bookCount;
	}
	public void setBookCount(Integer bookCount) {
		this.bookCount = bookCount;
	}
	
	

}
