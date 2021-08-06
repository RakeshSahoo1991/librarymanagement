/**
 * 
 */
package com.hexad.librarymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hexad.librarymanagement.models.ErrorModel;


/**
 * This class is responsible for handling all exception in the application
 * @author srake
 *
 */
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final String BOOK_ALREADY_BORROWED_BY_USER ="BOOK_ALREADY_BORROWED_BY_USER";
	
	private static final String BORROW_LIMIT_EXCEED ="BORROW_LIMIT_EXCEED";
	
	private static final String GENERAL_ERROR ="GENERAL_ERROR";
	
	private static final String BOOK_NOT_AVAILABLE ="BOOK_NOT_AVAILABLE";
	
	
	@ExceptionHandler(value = { BookAlreadyBorrowedByUserException.class })
    public ResponseEntity<ErrorModel> handleInvalidAge(BookAlreadyBorrowedByUserException ex) {
		ErrorModel errorModel = new ErrorModel(BOOK_ALREADY_BORROWED_BY_USER, ex.getMessage());
        return new ResponseEntity<ErrorModel>(errorModel,HttpStatus.CONFLICT);

    }
	
	@ExceptionHandler(value = { BorrowedLimitException.class })
    public ResponseEntity<ErrorModel> handleInvalidAge(BorrowedLimitException ex) {
		ErrorModel errorModel = new ErrorModel(BORROW_LIMIT_EXCEED, ex.getMessage());
        return new ResponseEntity<ErrorModel>(errorModel,HttpStatus.CONFLICT);

    }
	
	@ExceptionHandler(value = { BookNotAvilableException.class })
    public ResponseEntity<ErrorModel> handleBookingError(BookNotAvilableException ex) {
		ErrorModel errorModel = new ErrorModel(BOOK_NOT_AVAILABLE, ex.getMessage());
        return new ResponseEntity<ErrorModel>(errorModel,HttpStatus.BAD_REQUEST);

    }
	
	@ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ErrorModel> handleBookingError(Exception ex) {
		ErrorModel errorModel = new ErrorModel(GENERAL_ERROR, ex.getMessage());
        return new ResponseEntity<ErrorModel>(errorModel,HttpStatus.INTERNAL_SERVER_ERROR);

    }
	
}
