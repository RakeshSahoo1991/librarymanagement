/**
 * 
 */
package com.hexad.librarymanagement.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.hexad.librarymanagement.datamodel.BookDAO;

/**
 * @author srake
 *
 */
@Repository
public class LibraryRepositoryImpl implements LibraryRepository {

	// TODO replace this map with database
	private static Map<Integer, BookDAO> bookData = new HashMap<>();

	private static Integer bookIdCounter = 0;

	static{
		BookDAO book1 = new BookDAO(++bookIdCounter,"book1","author1",1);
		BookDAO book2 = new BookDAO(++bookIdCounter,"book2","author2",2);
		BookDAO book3 = new BookDAO(++bookIdCounter,"book3","author3",1);
		bookData.put(book1.getBookId(), book1);
		bookData.put(book2.getBookId(), book2);
		bookData.put(book3.getBookId(), book3);
	}
	
	/**
	 * This method fetch all the books available in the Library
	 * 
	 * @return List of Books
	 */
	public List<BookDAO> getAllTheBooks() {
		return bookData.values().stream().collect(Collectors.toList());
	}

	/**
	 * This method new book to the library or update the book to the library
	 * 
	 * @param book
	 * @return
	 */
	public BookDAO addUpdateBookToLibrary(BookDAO book) {
		if (book.getBookId() == null)
			book.setBookId(++bookIdCounter);

		bookData.put(book.getBookId(), book);
		return book;
	}
	
	/**
	 * This method fetch a book by given bookId
	 * @param bookId
	 * @return book entity
	 */
	public BookDAO getBookById(Integer bookId) {
		return bookData.get(bookId);
		
	}
	

}
