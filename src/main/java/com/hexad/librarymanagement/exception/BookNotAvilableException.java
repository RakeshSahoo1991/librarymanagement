/**
 * 
 */
package com.hexad.librarymanagement.exception;

/**
 * @author srake
 *
 */
public class BookNotAvilableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8707088302640178940L;
	
	public BookNotAvilableException(String message) {
		super(message);
	}

}
