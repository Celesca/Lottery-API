package com.kbtg.bootcamp.posttest.user.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserGetMyLotteriesResponse {

	private List<String> tickets;
	private Integer count;
	private Integer cost;

	public UserGetMyLotteriesResponse() {
	}

	public UserGetMyLotteriesResponse(List<String> tickets, Integer count, Integer cost) {
		this.tickets = tickets;
		this.count = count;
		this.cost = cost;
	}

}
