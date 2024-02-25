package com.kbtg.bootcamp.posttest.controller;

import com.kbtg.bootcamp.posttest.model.UserTicket;
import com.kbtg.bootcamp.posttest.response.UserBuyLotteriesResponse;
import com.kbtg.bootcamp.posttest.response.UserGetAllLotteriesResponse;
import com.kbtg.bootcamp.posttest.response.UserLotteriesResponse;
import com.kbtg.bootcamp.posttest.service.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
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
    public UserBuyLotteriesResponse BuyLottery(
            @Valid
            @PathVariable("userId") Integer userId,
            @PathVariable("ticketId") Integer ticketId) {
        return userService.buyLotteries(userId, ticketId);
    }

    // List all my lottery tickets
    @GetMapping("/users/{userId}/lotteries")
    public UserLotteriesResponse getMyLotteries(@Valid
                                               @PathVariable("userId") Integer userId) {
        return userService.getMyLotteries(userId);
    }

    // Sell back my lottery ticket
    @DeleteMapping("/users/{userId}/lotteries/{ticketId}")
    public String sellBackMyLotteries(@PathVariable("userId") Integer userId,
                                      @PathVariable("ticketId") String ticketId) {
        return "Sell back my lotteries success";
    }

}