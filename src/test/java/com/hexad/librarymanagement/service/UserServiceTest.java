/**
 * 
 */
package com.hexad.librarymanagement.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.hexad.librarymanagement.datamodel.UserDAO;
import com.hexad.librarymanagement.mapper.DataMapper;
import com.hexad.librarymanagement.models.User;
import com.hexad.librarymanagement.repository.UserRepository;

/**
 * @author srake
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@InjectMocks
	UserService userService;
	
	@Mock
	private UserRepository userRepositoryMock;

	@Mock
	private DataMapper dataMapperMock;

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);
	}


	/**
	 * Test to verify add a new user
	 */
	@Test
	public void testUpdateUser() {
		when(userRepositoryMock.addOrUpdateUser(Mockito.any(UserDAO.class)))
		.thenReturn(new UserDAO(1, "User1", "Email:user1@gmail.com"));
		when(dataMapperMock.mapToUserDAO(Mockito.any(User.class))).thenReturn(getDAOMapperUser());
		when(dataMapperMock.mapToUser(Mockito.any(UserDAO.class))).thenReturn(getResponseUser());
		User user = userService.updateUser(getRequestUser());
		assertEquals(new Integer(1), user.getUserId());
		verify(userRepositoryMock, times(1)).addOrUpdateUser(Mockito.any(UserDAO.class));
	}
	
	private User getResponseUser() {
		User user = new User();
		user.setUserId(1);
		user.setName("User1");
		user.setContactDtails("Email:user1@gmail.com");
		return user;
	}
	
	private User getRequestUser() {
		User user = new User();
		user.setName("User1");
		user.setContactDtails("Email:user1@gmail.com");
		return user;
	}
	
	private UserDAO getDAOMapperUser() {
		UserDAO user = new UserDAO();
		user.setName("User1");
		user.setContactDtails("Email:user1@gmail.com");
		return user;
	}
}
