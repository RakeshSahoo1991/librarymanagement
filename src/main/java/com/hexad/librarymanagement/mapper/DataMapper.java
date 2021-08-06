/**
 * 
 */
package com.hexad.librarymanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hexad.librarymanagement.datamodel.BookDAO;
import com.hexad.librarymanagement.datamodel.UserDAO;
import com.hexad.librarymanagement.models.Book;
import com.hexad.librarymanagement.models.User;

/**
 * This class is used to map the model object to data model object and vice versa
 * @author srake
 *
 */
@Component
public class DataMapper {
	
	/**
	 * This method maps list of book data model to response model
	 * @param bookDaoList
	 * @return List of book models
	 */
	public List<Book> mapToBookList(List<BookDAO> bookDaoList){
		List<Book> bookList = new ArrayList<>();
		
		bookDaoList.forEach(bookDAO -> bookList.add(mapToBook(bookDAO)));
		
		return bookList;
	}
	
	/**
	 * 
	 * @param book
	 * @return
	 */
	public BookDAO mapToBookDAO(Book book) {
		BookDAO bookDao = new BookDAO();
		bookDao.setBookId(book.getBookId());
		bookDao.setName(book.getName());
		bookDao.setAuthor(book.getAuthor());
		bookDao.setBookCount(book.getBookCount());
		return bookDao;	
	}
	
	/**
	 * 
	 * @param bookDao
	 * @return
	 */
	public Book mapToBook(BookDAO bookDao) {
		Book book = new Book();
		book.setBookId(bookDao.getBookId());
		book.setName(bookDao.getName());
		book.setAuthor(bookDao.getAuthor());
		book.setBookCount(bookDao.getBookCount());
		return book;	
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public UserDAO mapToUserDAO(User user) {
		UserDAO userDao = new UserDAO();
		userDao.setUserId(user.getUserId());
		userDao.setName(user.getName());
		userDao.setContactDtails(user.getContactDtails());
		userDao.setBorrowList(user.getBorrowList());
		return userDao;
	}
	
	/**
	 * 
	 * @param userDao
	 * @return
	 */
	public User mapToUser(UserDAO userDao) {
		User user = new User();
		user.setUserId(userDao.getUserId());
		user.setName(userDao.getName());
		user.setContactDtails(userDao.getContactDtails());
		user.setBorrowList(userDao.getBorrowList());
		return user;
	}


}
