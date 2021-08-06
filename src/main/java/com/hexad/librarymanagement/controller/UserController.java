/**
 * 
 */
package com.hexad.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexad.librarymanagement.models.User;
import com.hexad.librarymanagement.service.UserService;

/**
 * This controller class exposes all the user management related rest end points
 * 
 * @author srake
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * This rest end point is responsible of add or update user to the system
	 * 
	 * @param user
	 * @return updated user entity
	 */
	@PostMapping(value = "/adduser", produces = "application/json", consumes = "application/json")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);

	}

}
