/**
 * 
 */
package com.hexad.librarymanagement.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexad.librarymanagement.models.Book;

/**
 * This class performs integration testing for library management from
 * controller class
 * 
 * @author srake
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class LibraryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Order(1)
	void shouldAddNewBookToLibrary() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/library/addbook").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(getRequestBook()))).andExpect(status().isCreated());
	}

	@Test
	@Order(2)
	void shouldReturnBookListWithOkStatus() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/library/viewallbooks")).andExpect(status().isOk());
	}

	@Test
	@Order(3)
	void shouldBorrowBookSuccessfullyUserOne() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/library/borrowbook/{userId}/{bookId}", 1, 2))
				.andExpect(status().isOk());
	}

	@Test
	@Order(4)
	void shouldThrowBookAlreadyBorrowedByUserOneException() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/library/borrowbook/{userId}/{bookId}", 1, 2))
				.andExpect(status().isConflict());
	}

	@Test
	@Order(5)
	void shouldBorrowBookSuccessfullyUserTwo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/library/borrowbook/{userId}/{bookId}", 2, 2))
				.andExpect(status().isOk());
	}

	@Test
	@Order(6)
	void shouldBorrowTwoBooksSuccessfullyUserOne() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/library/borrowbook/{userId}/{bookId}", 1, 1))
				.andExpect(status().isOk());
	}

	@Test
	@Order(7)
	void shouldThrowBookNotAvilableExceptionForUserTwo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/library/borrowbook/{userId}/{bookId}", 2, 1))
				.andExpect(status().isBadRequest());
	}

	@Test
	@Order(8)
	void shouldThrowBorrowedLimitExceptionForUserOne() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/library/borrowbook/{userId}/{bookId}", 1, 3))
				.andExpect(status().isConflict());
	}

	@Test
	@Order(9)
	void shouldReturnBookToLibrary() throws Exception {
		List<Integer> bookIdList = new ArrayList<>();
		bookIdList.add(1);
		mockMvc.perform(MockMvcRequestBuilders.post("/library/returnbook/{userId}", 1)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(bookIdList)))
				.andExpect(status().isOk());
	}

	private Book getRequestBook() {
		Book book = new Book();
		book.setAuthor("Test Author");
		book.setName("Test Book");
		book.setBookCount(2);
		return book;
	}

}
