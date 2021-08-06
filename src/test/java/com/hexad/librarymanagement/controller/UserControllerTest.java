/**
 * 
 */
package com.hexad.librarymanagement.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexad.librarymanagement.models.User;

/**
 * @author srake
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	@Order(1)
	void shouldAddNewBookToLibrary() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/adduser").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(getRequestUser()))).andExpect(status().isOk()).andReturn();
		
		String resultContent = result.getResponse().getContentAsString();
		System.out.println(resultContent);
	}

	private User getRequestUser() {
		User user = new User();
		user.setName("TestUser");
		user.setContactDtails("TestUser@gmail.com");
		return user;
	}

}
