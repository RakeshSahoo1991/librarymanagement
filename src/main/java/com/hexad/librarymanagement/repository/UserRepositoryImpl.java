/**
 * 
 */
package com.hexad.librarymanagement.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hexad.librarymanagement.datamodel.UserDAO;

/**
 * @author srake
 *
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
	
	//TODO replace this map with database 
	private static Map<Integer, UserDAO> userDate = new HashMap<>();
	
	//This act as a auto generated unique id in hibernate
	private static Integer userIdCounter=0;
	
	static {
		UserDAO user = new UserDAO(++userIdCounter, "User1", "Email:user1@gmail.com");
		UserDAO user1 = new UserDAO(++userIdCounter, "User2", "Email:user2@gmail.com");
		userDate.put(user.getUserId(), user);
		userDate.put(user1.getUserId(), user1);
	}
	
	/**
	 * This method finds the user details by user id
	 * @param id
	 * @return
	 */
	public UserDAO getUserById(Integer id) {
		
		return userDate.get(id);
	}
	
	/**
	 * This method user add new user or update existing user details.
	 * @param user
	 * @return
	 */
	//TODO This can be break into 2 different methods if needed
	public UserDAO addOrUpdateUser(UserDAO user) {
		if(user.getUserId() == null)
			user.setUserId(++userIdCounter);
		
		userDate.put(user.getUserId(), user);
		
		return user;
		
	}

}
