package com.kbtg.bootcamp.posttest.controller;

import com.kbtg.bootcamp.posttest.service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/lotteries")
    public String createLottery(@RequestBody createLotteryRequest request) {
        return adminService.createLottery(request);
    }

}
