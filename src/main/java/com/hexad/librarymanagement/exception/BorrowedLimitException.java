/**
 * 
 */
package com.hexad.librarymanagement.exception;

/**
 * @author srake
 *
 */
public class BorrowedLimitException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1266967358682567488L;
	
	public BorrowedLimitException(String message) {
		super(message);
	}

}
