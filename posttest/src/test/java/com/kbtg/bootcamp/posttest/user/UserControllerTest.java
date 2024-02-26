package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.user.controller.UserController;
import com.kbtg.bootcamp.posttest.user.request.UserBuySellRequestDto;
import com.kbtg.bootcamp.posttest.user.response.UserBuyLotteriesResponse;
import com.kbtg.bootcamp.posttest.user.response.UserGetAllLotteriesResponse;
import com.kbtg.bootcamp.posttest.user.response.UserGetMyLotteriesResponse;
import com.kbtg.bootcamp.posttest.user.response.UserSellLotteriesResponse;
import com.kbtg.bootcamp.posttest.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	MockMvc mockMvc;

	@Mock
	UserService userService;

	@BeforeEach
	void setUp() {
		UserController userController = new UserController(userService);
		mockMvc = MockMvcBuilders.standaloneSetup(userController)
				.alwaysDo(print())
				.build();
	}


	// GET: /lotteries section
	@Test
	@DisplayName("when perform GET: /lotteries on empty lotteries, then return 200 OK and empty list of lotteries")
	void getAllLotteries() throws Exception {
		when(userService.getAllLotteries()).thenReturn(new UserGetAllLotteriesResponse());

		String expected = "{\"tickets\":null}";
		mockMvc.perform(get("/lotteries"))
				.andExpect(status().isOk())
				.andExpect(result -> assertEquals(expected, result.getResponse().getContentAsString()));
	}

	@Test
	@DisplayName("when perform GET: /lotteries on 1 lottery, then return 200 OK and list of one lottery")
	void getAllLotteriesOne() throws Exception {
		UserGetAllLotteriesResponse response = new UserGetAllLotteriesResponse();
		response.setTickets(List.of("123456"));
		when(userService.getAllLotteries()).thenReturn(response);

		String expected = "{\"tickets\":[\"123456\"]}";
		mockMvc.perform(get("/lotteries"))
				.andExpect(status().isOk())
				.andExpect(result -> assertEquals(expected, result.getResponse().getContentAsString()));
	}

	@Test
	@DisplayName("when perform GET: /lotteries on non-empty lotteries, then return 200 OK and list of lotteries")
	void getAllLotteriesNotEmpty() throws Exception {
		UserGetAllLotteriesResponse response = new UserGetAllLotteriesResponse();
		response.setTickets(List.of("123456", "123457", "123458"));
		when(userService.getAllLotteries()).thenReturn(response);

		String expected = "{\"tickets\":[\"123456\",\"123457\",\"123458\"]}";
		mockMvc.perform(get("/lotteries"))
				.andExpect(status().isOk())
				.andExpect(result -> assertEquals(expected, result.getResponse().getContentAsString()));
	}

	// POST: /users/{userId}/lotteries/{ticketId} section
	@Test
	@DisplayName("when perform POST: /users/{userId}/lotteries/{ticketId} on valid request, then return 201 Created and ticketId")
	void buyLotterySuccess() throws Exception {
		String userId = "0123456789";
		String ticketId = "123456";

		when(userService.buyLotteries(any(UserBuySellRequestDto.class)))
				.thenReturn(new UserBuyLotteriesResponse(1));

		mockMvc.perform(post("/users/{userId}/lotteries/{ticketId}", userId, ticketId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));

		verify(userService).buyLotteries(any(UserBuySellRequestDto.class));
		verifyNoMoreInteractions(userService);

	}

	@Test
	@DisplayName("when perform POST: /users/{userId}/lotteries/{ticketId} on invalid request, then return 400 Bad Request")
	void buyLotteryInvalid() throws Exception {
		String userId = "01234567890";
		String ticketId = "123456";

		mockMvc.perform(post("/users/{userId}/lotteries/{ticketId}", userId, ticketId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	// GET: /users/{userId}/lotteries section
	@Test
	@DisplayName("when perform GET: /users/{userId}/lotteries on empty user tickets, then return 200 OK and empty list of user tickets")
	void getMyLotteriesEmpty() throws Exception {
		String userId = "01234567890";
		when(userService.getMyLotteries(userId)).thenReturn(new UserGetMyLotteriesResponse());

		String expected = "{\"tickets\":null,\"count\":null,\"cost\":null}";
		mockMvc.perform(get("/users/{userId}/lotteries", userId))
				.andExpect(status().isOk())
				.andExpect(result -> assertEquals(expected, result.getResponse().getContentAsString()));
	}

	@Test
	@DisplayName("when perform GET: /users/{userId}/lotteries on 1 user ticket, then return 200 OK and list of one user ticket")
	void getMyLotteriesOne() throws Exception {
		String userId = "01234567890";
		UserGetMyLotteriesResponse response = new UserGetMyLotteriesResponse();
		response.setTickets(List.of("123456"));
		response.setCount(1);
		response.setCost(100);
		when(userService.getMyLotteries(userId)).thenReturn(response);

		String expected = "{\"tickets\":[\"123456\"],\"count\":1,\"cost\":100}";
		mockMvc.perform(get("/users/{userId}/lotteries", userId))
				.andExpect(status().isOk())
				.andExpect(result -> assertEquals(expected, result.getResponse().getContentAsString()));
	}

	@Test
	@DisplayName("when perform GET: /users/{userId}/lotteries on non-empty user tickets, then return 200 OK and list of user tickets")
	void getMyLotteriesNotEmpty() throws Exception {
		String userId = "01234567890";
		UserGetMyLotteriesResponse response = new UserGetMyLotteriesResponse();
		response.setTickets(List.of("123456", "123457", "123458"));
		response.setCount(3);
		response.setCost(300);
		when(userService.getMyLotteries(userId)).thenReturn(response);

		String expected = "{\"tickets\":[\"123456\",\"123457\",\"123458\"],\"count\":3,\"cost\":300}";
		mockMvc.perform(get("/users/{userId}/lotteries", userId))
				.andExpect(status().isOk())
				.andExpect(result -> assertEquals(expected, result.getResponse().getContentAsString()));
	}

	// DELETE: /users/{userId}/lotteries/{ticketId} section
	@Test
	@DisplayName("when perform DELETE: /users/{userId}/lotteries/{ticketId} on valid request, then return 200 OK and ticketId")
	void sellLotteriesEmpty() throws Exception {

		String userId = "0123456789";
		String ticketId = "123456";


		when(userService.sellLotteries(any(UserBuySellRequestDto.class)))
				.thenReturn(new UserSellLotteriesResponse(ticketId));

		mockMvc.perform(delete("/users/{userId}/lotteries/{ticketId}", userId, ticketId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.ticket").isString())
				.andExpect(jsonPath("$.ticket").value(ticketId));

	}

	@Test
	@DisplayName("when perform DELETE: /users/{userId}/lotteries/{ticketId} on invalid request, then return 400 Bad Request")
	void sellLotteriesInvalid() throws Exception {

		String userId = "0123456789";
		String invalidTicketId = "invalidTicketId";

		mockMvc.perform(delete("/users/{userId}/lotteries/{ticketId}", userId, invalidTicketId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
}