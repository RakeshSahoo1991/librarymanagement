/**
 * 
 */
package com.hexad.librarymanagement.datamodel;

/**
 * @author srake
 *
 */
public class BookDAO {
	
	private Integer bookId;
	private String name;
	private String author;
	private Integer bookCount;
	
	public BookDAO(Integer bookId,String name, String author, Integer bookCount) {
		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.bookCount = bookCount;
	}
	
	public BookDAO() {
	}

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
