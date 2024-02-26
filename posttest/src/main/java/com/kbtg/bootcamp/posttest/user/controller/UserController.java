package com.kbtg.bootcamp.posttest.user.controller;

import com.kbtg.bootcamp.posttest.user.request.UserBuySellRequestDto;
import com.kbtg.bootcamp.posttest.user.response.UserBuyLotteriesResponse;
import com.kbtg.bootcamp.posttest.user.response.UserGetAllLotteriesResponse;
import com.kbtg.bootcamp.posttest.user.response.UserGetMyLotteriesResponse;
import com.kbtg.bootcamp.posttest.user.response.UserSellLotteriesResponse;
import com.kbtg.bootcamp.posttest.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	// See list of lotteries
	@GetMapping("/lotteries")
	@ResponseStatus(code = HttpStatus.OK)
	public UserGetAllLotteriesResponse getLotteries() {
		return userService.getAllLotteries();
	}


	// Buy lotteries
	@PostMapping("/users/{userId}/lotteries/{ticketId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserBuyLotteriesResponse BuyLottery(@Valid UserBuySellRequestDto requestDto) {
		return userService.buyLotteries(requestDto);
	}

	// List all my lottery tickets
	@GetMapping("/users/{userId}/lotteries")
	@ResponseStatus(code = HttpStatus.OK)
	public UserGetMyLotteriesResponse getMyLotteries(@Valid @PathVariable("userId") String userId) {
		return userService.getMyLotteries(userId);
	}

	// Sell back my lottery ticket
	@DeleteMapping("/users/{userId}/lotteries/{ticketId}")
	@ResponseStatus(code = HttpStatus.OK)
	public UserSellLotteriesResponse sellLotteries(@Valid UserBuySellRequestDto requestDto) {
		return userService.sellLotteries(requestDto);
	}

}