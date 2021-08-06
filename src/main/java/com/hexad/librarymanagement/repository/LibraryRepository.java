/**
 * 
 */
package com.hexad.librarymanagement.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hexad.librarymanagement.datamodel.BookDAO;

/**
 * @author srake
 *
 */
@Component
public interface LibraryRepository {
	
	List<BookDAO> getAllTheBooks();
	
	BookDAO addUpdateBookToLibrary(BookDAO book); 
	
	BookDAO getBookById(Integer bookId);

}
