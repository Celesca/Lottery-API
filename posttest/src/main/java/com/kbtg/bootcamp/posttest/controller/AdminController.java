package com.kbtg.bootcamp.posttest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/lotteries")
    public String createLotteries(@RequestBody createLotteriesRequest request) {
        return "Create lotteries success";

    }

}
