package com.kbtg.bootcamp.posttest.admin;

import com.kbtg.bootcamp.posttest.admin.controller.AdminController;
import com.kbtg.bootcamp.posttest.admin.request.createLotteryRequestDto;
import com.kbtg.bootcamp.posttest.admin.response.createLotteryResponse;
import com.kbtg.bootcamp.posttest.admin.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

	MockMvc mockMvc;
	@Mock
	AdminService adminService;

	@BeforeEach
	void setUp() {
		AdminController adminController = new AdminController(adminService);
		mockMvc = MockMvcBuilders.standaloneSetup(adminController)
				.alwaysDo(print())
				.build();
	}

	@Test
	@DisplayName("when perform on POST: /admin/lotteries, then return 201 Created and ticketId")
	void createLotterySuccess() throws Exception {

		when(adminService.createLottery(any())).thenReturn(new createLotteryResponse("123456"));

		String expectedResponse = "{\"ticket\":\"123456\"}";
		mockMvc.perform(post("/admin/lotteries")
				.contentType("application/json")
				.content("{\"ticket\":\"123456\",\"price\":100,\"amount\":10}"))
				.andExpect(status().isCreated())
				.andExpect(result -> assertEquals( expectedResponse
						, result.getResponse().getContentAsString()));
	}

	@Test
	@DisplayName("adminService when there are ticket already exists, then return 201 Created and ticketId")
	void createLotterySuccessTicketExists() throws Exception {

		when(adminService.createLottery(any())).thenReturn(new createLotteryResponse
				("123456"));

		String expectedResponse = "{\"ticket\":\"123456\"}";
		mockMvc.perform(post("/admin/lotteries")
				.contentType("application/json")
				.content("{\"ticket\":\"123456\",\"price\":100,\"amount\":10}"))
				.andExpect(status().isCreated())
				.andExpect(result -> assertEquals( expectedResponse
						, result.getResponse().getContentAsString()));
	}

	@Test
	@DisplayName("when insert negative price " +
			"POST: /admin/lotteries, then return 400 Bad Request")
	void createLotteryNegativePrice() throws Exception {

		mockMvc.perform(post("/admin/lotteries")
				.contentType("application/json")
				.content("{\"ticket\":\"12345\",\"price\":-100,\"amount\":10}"))
				.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("when insert negative amount " +
			"POST: /admin/lotteries, then return 400 Bad Request")
	void createLotteryNegativeAmount() throws Exception {

			mockMvc.perform(post("/admin/lotteries")
					.contentType("application/json")
					.content("{\"ticket\":\"12345\",\"price\":100,\"amount\":-10}"))
					.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("when insert ticket with length less than 6 " +
			"POST: /admin/lotteries, then return 400 Bad Request")
	void createLotteryTicketIdLessThan6() throws Exception {

			mockMvc.perform(post("/admin/lotteries")
					.contentType("application/json")
					.content("{\"ticket\":\"12345\",\"price\":100,\"amount\":10}"))
					.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("when insert ticket with length more than 6 " +
			"POST: /admin/lotteries, then return 400 Bad Request")
	void createLotteryTicketIdMoreThan6() throws Exception {

				mockMvc.perform(post("/admin/lotteries")
						.contentType("application/json")
						.content("{\"ticket\":\"1234567\",\"price\":100,\"amount\":10}"))
						.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("when insert ticket with wrong pattern " +
			"POST: /admin/lotteries, then return 400 Bad Request")
	void createLotteryTicketIdWrongPattern() throws Exception {

			mockMvc.perform(post("/admin/lotteries")
					.contentType("application/json")
					.content("{\"ticket\":\"1234a6\",\"price\":100,\"amount\":10}"))
					.andExpect(status().isBadRequest());
	}

	// Test adminService
	@Test
	@DisplayName("when ticket already exists, then return 201 Created and ticketId")
	void createExistedTicket() {
		// Arrange
		createLotteryRequestDto request = new createLotteryRequestDto("123456", 100, 10);
		when(adminService.createLottery(request)).thenReturn(new createLotteryResponse("123456"));

		// Act
		createLotteryResponse response = adminService.createLottery(request);

		// Assert
		assertEquals("123456", response.getTicket());
	}

}