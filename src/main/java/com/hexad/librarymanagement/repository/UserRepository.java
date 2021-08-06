/**
 * 
 */
package com.hexad.librarymanagement.repository;

import org.springframework.stereotype.Component;

import com.hexad.librarymanagement.datamodel.UserDAO;

/**
 * @author srake
 *
 */
@Component
public interface UserRepository {
	
	UserDAO getUserById(Integer id);
	
	UserDAO addOrUpdateUser(UserDAO user);

}
