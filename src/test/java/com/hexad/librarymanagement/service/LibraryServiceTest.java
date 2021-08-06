/**
 * 
 */
package com.hexad.librarymanagement.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

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
 * @author srake
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceTest {

	@InjectMocks
	LibraryService libraryService;

	@Mock
	private LibraryRepository libraryRepositoryMock;

	@Mock
	private UserRepository userRepositoryMock;

	@Mock
	private DataMapper dataMapperMock;

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test to verify available books in the library
	 */
	@Test
	public void testViewAllBooksInLibrary() {
		when(libraryRepositoryMock.getAllTheBooks()).thenReturn(getDaoBookList());
		when(dataMapperMock.mapToBookList(Mockito.any())).thenReturn(getResponseBookList());
		List<Book> bookList = libraryService.viewAllBooksInLibrary();
		assertEquals(2, bookList.size());
		verify(libraryRepositoryMock, times(1)).getAllTheBooks();
	}

	/**
	 * Test to verify successful borrowing process
	 */
	@Test
	public void testBorrowBookFromLibrary() {
		when(libraryRepositoryMock.getBookById(Mockito.anyInt()))
				.thenReturn(new BookDAO(1, "TestBook1", "TestAuthor1", 1));
		when(userRepositoryMock.getUserById(Mockito.anyInt()))
				.thenReturn(new UserDAO(1, "User1", "Email:user1@gmail.com"));
		when(libraryRepositoryMock.addUpdateBookToLibrary(Mockito.any(BookDAO.class)))
				.thenReturn(new BookDAO(1, "TestBook1", "TestAuthor1", 0));
		UserDAO daoUser = new UserDAO(1, "User1", "Email:user1@gmail.com");
		daoUser.getBorrowList().add(1);
		when(userRepositoryMock.addOrUpdateUser(Mockito.any(UserDAO.class))).thenReturn(daoUser);
		when(dataMapperMock.mapToUser(Mockito.any(UserDAO.class))).thenReturn(getResponseUser());
		User user = libraryService.borrowBookFromLibrary(1, 1);
		assertEquals(1, user.getBorrowList().size());
	}

	/**
	 * Test to verify borrowing limit per user
	 */
	@Test(expected = BorrowedLimitException.class)
	public void testBorrowMoreThenBookLimit() {
		when(libraryRepositoryMock.getBookById(Mockito.anyInt()))
				.thenReturn(new BookDAO(1, "TestBook1", "TestAuthor1", 1));
		UserDAO daoUserRequested = new UserDAO(1, "User1", "Email:user1@gmail.com");
		daoUserRequested.getBorrowList().add(1);
		daoUserRequested.getBorrowList().add(2);
		when(userRepositoryMock.getUserById(Mockito.anyInt())).thenReturn(daoUserRequested);
		libraryService.borrowBookFromLibrary(1, 1);
	}

	/**
	 * Test to verify borrow multiple copies of a book per user
	 */
	@Test(expected = BookAlreadyBorrowedByUserException.class)
	public void testBorrowMoreThenOneCopyOfBook() {
		when(libraryRepositoryMock.getBookById(Mockito.anyInt()))
				.thenReturn(new BookDAO(1, "TestBook1", "TestAuthor1", 1));
		UserDAO daoUserRequested = new UserDAO(1, "User1", "Email:user1@gmail.com");
		daoUserRequested.getBorrowList().add(1);
		when(userRepositoryMock.getUserById(Mockito.anyInt())).thenReturn(daoUserRequested);
		libraryService.borrowBookFromLibrary(1, 1);
	}

	/**
	 * Test to verify borrow not available book
	 */
	@Test(expected = BookNotAvilableException.class)
	public void testBorrowNotAvailableBook() {
		when(libraryRepositoryMock.getBookById(Mockito.anyInt()))
				.thenReturn(new BookDAO(1, "TestBook1", "TestAuthor1", 0));
		libraryService.borrowBookFromLibrary(1, 1);
	}
	
	/**
	 * Test to verify return process
	 */
	@Test
	public void testReturnBooksToLibrary() {
		when(libraryRepositoryMock.getBookById(Mockito.anyInt()))
				.thenReturn(new BookDAO(2, "TestBook1", "TestAuthor1", 1));
		UserDAO daoUserRequested = new UserDAO(1, "User1", "Email:user1@gmail.com");
		daoUserRequested.getBorrowList().add(1);
		daoUserRequested.getBorrowList().add(2);
		when(userRepositoryMock.getUserById(Mockito.anyInt())).thenReturn(daoUserRequested);
		when(dataMapperMock.mapToUser(Mockito.any(UserDAO.class))).thenReturn(getResponseUser());
		UserDAO daoUserupdated = new UserDAO(1, "User1", "Email:user1@gmail.com");
		daoUserRequested.getBorrowList().add(1);
		when(userRepositoryMock.addOrUpdateUser(Mockito.any(UserDAO.class))).thenReturn(daoUserupdated);
		List<Integer> bookIdList = new ArrayList<Integer>();
		bookIdList.add(2);
		User user =libraryService.returnBookToLibrary(1, bookIdList);
		assertEquals(1, user.getBorrowList().size());
	}

	private User getResponseUser() {
		User user = new User();
		user.setUserId(1);
		user.setName("User1");
		user.setContactDtails("Email:user1@gmail.com");
		user.getBorrowList().add(1);
		return user;
	}

	private List<BookDAO> getDaoBookList() {
		List<BookDAO> bookList = new ArrayList<BookDAO>();
		bookList.add(new BookDAO(1, "TestBook1", "TestAuthor1", 1));
		bookList.add(new BookDAO(2, "TestBook2", "TestAuthor2", 2));
		return bookList;
	}

	private List<Book> getResponseBookList() {
		List<Book> bookList = new ArrayList<Book>();
		Book book1 = new Book();
		book1.setBookId(1);
		book1.setName("TestBook1");
		book1.setAuthor("TestAuthor1");
		book1.setBookCount(1);
		bookList.add(book1);

		Book book2 = new Book();
		book1.setBookId(2);
		book1.setName("TestBook2");
		book1.setAuthor("TestAuthor2");
		book1.setBookCount(2);
		bookList.add(book2);

		return bookList;
	}

}
