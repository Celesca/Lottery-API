package com.kbtg.bootcamp.posttest.controller;

import com.kbtg.bootcamp.posttest.model.Lottery;
import com.kbtg.bootcamp.posttest.service.UserService;
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
    public List<String> getLotteries() {
        return userService.getAllLotteries();
    }

    // Buy lotteries
    @PostMapping("/users/{userId}/lotteries/{ticketId}")
    public String BuyLotteries(@PathVariable Integer userId, @PathVariable Integer ticketId) {
        return "Buy lotteries success";
    }

    // List all my lottery tickets

    @GetMapping("/users/{userId}/lotteries")
    public String getMyLotteries(@PathVariable Integer userId) {
        return "Get my lotteries success";
    }

    // Sell back my lottery ticket
    @DeleteMapping("/users/{userId}/lotteries/{ticketId}")
    public String sellBackMyLotteries(@PathVariable Integer userId, @PathVariable Integer ticketId) {
        return "Sell back my lotteries success";
    }


}