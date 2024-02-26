package com.kbtg.bootcamp.posttest.admin.controller;

import com.kbtg.bootcamp.posttest.admin.service.AdminService;
import com.kbtg.bootcamp.posttest.admin.request.createLotteryRequestDto;
import com.kbtg.bootcamp.posttest.admin.response.createLotteryResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/admin")
public class AdminController {

	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@PostMapping("/lotteries")
	@ResponseStatus(code = HttpStatus.CREATED)
	public createLotteryResponse createLottery(@Valid @RequestBody createLotteryRequestDto request) {
		return adminService.createLottery(request);
	}

}