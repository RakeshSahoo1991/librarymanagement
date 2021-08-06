package com.hexad.librarymanagement.datamodel;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	
	private Integer userId;
	private String name;
	private String contactDtails;
	private List<Integer> borrowList;
	public UserDAO(Integer userId,String name,String contactDetails) {
		this.userId = userId;
		this.name = name;
		this.contactDtails = contactDetails;
	}
	public UserDAO() {
	}
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
