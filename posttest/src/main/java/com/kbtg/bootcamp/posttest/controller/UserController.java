package com.kbtg.bootcamp.posttest.controller;

import com.kbtg.bootcamp.posttest.response.UserBuyLotteriesResponse;
import com.kbtg.bootcamp.posttest.response.UserGetAllLotteriesResponse;
import com.kbtg.bootcamp.posttest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // See list of lotteries
    @GetMapping("/lotteries")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public UserGetAllLotteriesResponse getLotteries() {
        return userService.getAllLotteries();
    }


    // Buy lotteries
    @PostMapping("/users/{userId}/lotteries/{ticketId}")
    public UserBuyLotteriesResponse BuyLottery(@PathVariable("userId") Integer userId, @PathVariable("ticketId") Integer ticketId) {
        return userService.buyLotteries(userId, ticketId);
    }

    // List all my lottery tickets

    @GetMapping("/users/{userId}/lotteries")
    public String getMyLotteries(@PathVariable Integer userId) {
        return "Get my lotteries success";
    }

    // Sell back my lottery ticket
    @DeleteMapping("/users/{userId}/lotteries/{ticketId}")
    public String sellBackMyLotteries(@PathVariable Integer userId, @PathVariable String ticketId) {
        return "Sell back my lotteries success";
    }


}