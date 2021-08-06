/**
 * 
 */
package com.hexad.librarymanagement.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author srake
 *
 */
public class User {
	
	
	private Integer userId;
	private String name;
	private String contactDtails;
	private List<Integer> borrowList;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactDtails() {
		return contactDtails;
	}
	public void setContactDtails(String contactDtails) {
		this.contactDtails = contactDtails;
	}
	public List<Integer> getBorrowList() {
		if(borrowList == null)
			borrowList = new ArrayList<Integer>();
			
		return borrowList;
	}
	public void setBorrowList(List<Integer> borrowList) {
		this.borrowList = borrowList;
	}
	
	

}
