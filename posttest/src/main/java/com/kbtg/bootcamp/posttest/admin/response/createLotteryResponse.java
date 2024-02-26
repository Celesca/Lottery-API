package com.kbtg.bootcamp.posttest.admin.response;

import lombok.Getter;

@Getter
public class createLotteryResponse
{

	private final String ticket;

	public createLotteryResponse(String ticket) {
		this.ticket = ticket;
	}
}
