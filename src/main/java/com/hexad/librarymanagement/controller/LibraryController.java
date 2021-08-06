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
 * @author srake
 *
 */
@RestController
@RequestMapping("/library")
public class LibraryController {

	@Autowired
	LibraryService libraryService;

	@PostMapping(value = "/addbook", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Book> addBookToLibrary(@RequestBody Book bookRequest) {
		return new ResponseEntity<Book>(libraryService.addNewBookToLibrary(bookRequest), HttpStatus.OK);
	}

	@GetMapping(value = "/viewallbooks", produces = "application/json")
	public ResponseEntity<List<Book>> viewAllBooksInLibrary() {
		return new ResponseEntity<List<Book>>(libraryService.viewAllBooksInLibrary(), HttpStatus.OK);

	}

	@PostMapping(value = "/borrowbook/{userId}/{bookId}", produces = "application/json")
	public ResponseEntity<User> borrowBookFromLibrary(@PathVariable("userId") Integer userId,
			@PathVariable("bookId") Integer bookId) {
		return new ResponseEntity<User>(libraryService.borrowBookFromLibrary(userId, bookId), HttpStatus.OK);
	}

	@PostMapping(value = "/returnbook/{userId}", produces = "application/json")
	public ResponseEntity<User> returnBookToLibrary(@PathVariable("userId") Integer userId,
			@RequestBody List<Integer> bookIds) {
		return new ResponseEntity<User>(libraryService.returnBookToLibrary(userId, bookIds), HttpStatus.OK);
	}

}
