package com.kbtg.bootcamp.posttest.controller;

import com.kbtg.bootcamp.posttest.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/lotteries")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String createLottery(@RequestBody createLotteryRequest request) {
        return adminService.createLottery(request);
    }

}