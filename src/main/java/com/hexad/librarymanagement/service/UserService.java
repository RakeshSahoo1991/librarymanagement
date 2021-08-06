/**
 * 
 */
package com.hexad.librarymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexad.librarymanagement.mapper.DataMapper;
import com.hexad.librarymanagement.models.User;
import com.hexad.librarymanagement.repository.UserRepository;

/**
 * This service class has user related business logics
 * 
 * @author srake
 *
 */
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	DataMapper mapper;

	/**
	 * This method performs both add a new user or update existing user
	 * @param user
	 * @return updated user
	 */
	public User updateUser(User user) {
		return mapper.mapToUser(userRepository.addOrUpdateUser(mapper.mapToUserDAO(user)));
	}

}
