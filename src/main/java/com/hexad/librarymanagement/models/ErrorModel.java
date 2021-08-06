/**
 * 
 */
package com.hexad.librarymanagement.models;

/**
 * @author srake
 *
 */
public class ErrorModel {
	
	private String code;
	private String description;
	
	public ErrorModel(String errorCode,String errorMsg) {
		this.code = errorCode;
		this.description = errorMsg;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
