package com.kbtg.bootcamp.posttest.admin.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
public record createLotteryRequestDto(
	@NotNull
	@Size(min = 6, max = 6, message = "lotteryNumber must be 6 numeric digits")
	@Pattern(regexp = "^[0-9]{6}$", message = "lotteryNumber must be 6 numeric digits")
	String ticket,

	@NotNull
			@Positive
	Integer price,

	@NotNull
	@Positive
	Integer amount

){ }
