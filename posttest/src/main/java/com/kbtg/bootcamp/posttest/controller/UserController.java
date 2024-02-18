package com.kbtg.bootcamp.posttest.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    // See list of lotteries
    @GetMapping("/lotteries")
    public String getLotteries() {
        return "Get lotteries success";
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
