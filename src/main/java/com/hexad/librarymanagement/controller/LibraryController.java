/**
 * 
 */
package com.hexad.librarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexad.librarymanagement.models.Book;
import com.hexad.librarymanagement.models.User;
import com.hexad.librarymanagement.service.LibraryService;

/**
 * This controller exposes library management related rest end points
 * 
 * @author srake
 *
 */
@RestController
@RequestMapping("/library")
public class LibraryController {

	@Autowired
	LibraryService libraryService;

	/**
	 * This rest end point is responsible for add or update book to the library
	 * 
	 * @param bookRequest
	 * @return updated book entity
	 */
	@PostMapping(value = "/addbook", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Book> addBookToLibrary(@RequestBody Book bookRequest) {
		return new ResponseEntity<Book>(libraryService.addNewBookToLibrary(bookRequest), HttpStatus.CREATED);
	}

	/**
	 * This rest end point provides all the available books in the library
	 * 
	 * @return list of book entity
	 */
	@GetMapping(value = "/viewallbooks", produces = "application/json")
	public ResponseEntity<List<Book>> viewAllBooksInLibrary() {
		return new ResponseEntity<List<Book>>(libraryService.viewAllBooksInLibrary(), HttpStatus.OK);

	}

	/**
	 * This rest end point handles borrowing book from library functionality for
	 * users
	 * 
	 * @param userId
	 * @param bookId
	 * @return updated user entity
	 */
	@PostMapping(value = "/borrowbook/{userId}/{bookId}", produces = "application/json")
	public ResponseEntity<User> borrowBookFromLibrary(@PathVariable("userId") Integer userId,
			@PathVariable("bookId") Integer bookId) {
		return new ResponseEntity<User>(libraryService.borrowBookFromLibrary(userId, bookId), HttpStatus.OK);
	}

	/**
	 * This rest end point handles returning book to library functionality by users
	 * 
	 * @param userId
	 * @param bookIds
	 * @return updated user entity
	 */
	@PostMapping(value = "/returnbook/{userId}", produces = "application/json")
	public ResponseEntity<User> returnBookToLibrary(@PathVariable("userId") Integer userId,
			@RequestBody List<Integer> bookIds) {
		return new ResponseEntity<User>(libraryService.returnBookToLibrary(userId, bookIds), HttpStatus.OK);
	}

}
