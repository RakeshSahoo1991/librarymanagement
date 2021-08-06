/**
 * 
 */
package com.hexad.librarymanagement.exception;

/**
 * @author srake
 *
 */
public class BookAlreadyBorrowedByUserException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8231443441868527169L;

	public BookAlreadyBorrowedByUserException(String message) {
		super(message);
	}
}
