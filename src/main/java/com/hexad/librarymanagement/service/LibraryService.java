/**
 * 
 */
package com.hexad.librarymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexad.librarymanagement.datamodel.BookDAO;
import com.hexad.librarymanagement.datamodel.UserDAO;
import com.hexad.librarymanagement.exception.BookAlreadyBorrowedByUserException;
import com.hexad.librarymanagement.exception.BookNotAvilableException;
import com.hexad.librarymanagement.exception.BorrowedLimitException;
import com.hexad.librarymanagement.mapper.DataMapper;
import com.hexad.librarymanagement.models.Book;
import com.hexad.librarymanagement.models.User;
import com.hexad.librarymanagement.repository.LibraryRepository;
import com.hexad.librarymanagement.repository.UserRepository;

/**
 * This class has all the business logic for library management
 * 
 * @author srake
 *
 */
@Service
public class LibraryService {

	@Autowired
	LibraryRepository libraryRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	DataMapper mapper;

	/**
	 * This method retrieves all the available books in the library
	 * 
	 * @return List of books available in Library
	 */
	public List<Book> viewAllBooksInLibrary() {

		return mapper.mapToBookList(libraryRepository.getAllTheBooks());

	}

	/**
	 * This service method responsible to add new book to the library
	 * 
	 * @param book
	 * @return updated book entity
	 */
	public Book addNewBookToLibrary(Book book) {

		BookDAO bookDao = UpdateBookInLibrary(mapper.mapToBookDAO(book));

		return mapper.mapToBook(bookDao);

	}

	/**
	 * This method performs complete borrowing process
	 * 
	 * @param userId
	 * @param bookId
	 * @return user details with updated borrowed list
	 */
	public User borrowBookFromLibrary(Integer userId, Integer bookId) {
		BookDAO availableBook = checkForBookAvailability(bookId);
		UserDAO userDao = userRepository.getUserById(userId);
		checkForUserEligibility(userDao, bookId);

		// first update the book count in Library
		availableBook.setBookCount(availableBook.getBookCount() - 1);
		UpdateBookInLibrary(availableBook);

		// update the user borrow List
		userDao.getBorrowList().add(bookId);

		return mapper.mapToUser(userRepository.addOrUpdateUser(userDao));
	}

	/**
	 * This method performs complete book return process
	 * 
	 * @param userId
	 * @param bookIds
	 * @return user details with updated borrowed list
	 */
	public User returnBookToLibrary(Integer userId, List<Integer> bookIds) {
		UserDAO userDao = userRepository.getUserById(userId);
		for (Integer bookId : bookIds) {
			BookDAO bookDao = libraryRepository.getBookById(bookId);
			bookDao.setBookCount(bookDao.getBookCount() + 1);
			UpdateBookInLibrary(bookDao);
		}

		userDao.getBorrowList().removeAll(bookIds);

		return mapper.mapToUser(userRepository.addOrUpdateUser(userDao));

	}

	/**
	 * This method validates the eligibility of user before borrowing
	 * 
	 * @param userDao
	 * @param bookId
	 */
	private void checkForUserEligibility(UserDAO userDao, Integer bookId) {
		if (userDao.getBorrowList().size() >= 2)
			throw new BorrowedLimitException("You have already borrowed maximum number of book (Max Limit:2 books)");

		if (userDao.getBorrowList().contains(bookId))
			throw new BookAlreadyBorrowedByUserException("You have already borrowed a copy of this book");

	}

	/**
	 * This method checks the availability of book before borrowing
	 * 
	 * @param bookId
	 * @return available book
	 */
	private BookDAO checkForBookAvailability(Integer bookId) {
		BookDAO availableBook = libraryRepository.getBookById(bookId);

		if (availableBook == null || availableBook.getBookCount() < 1)
			throw new BookNotAvilableException(
					"This book is not avialable in Library at this moment, please check later");

		return availableBook;
	}

	/**
	 * This method updates the book in the library
	 * 
	 * @param book
	 * @return
	 */
	private BookDAO UpdateBookInLibrary(BookDAO book) {
		return libraryRepository.addUpdateBookToLibrary(book);
	}

}
