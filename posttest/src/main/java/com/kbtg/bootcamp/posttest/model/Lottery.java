package com.kbtg.bootcamp.posttest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "lottery")
public class Lottery {

	@Id
	@NotNull
	@Size(min = 6, max = 6, message = "lotteryNumber must be 6 digits")
	@Pattern(regexp = "^[0-9]{6}$", message = "lotteryNumber must be 6 numeric digits")
	private String ticketid;

	@NotNull
	@Positive
	private Integer price;

	@NotNull
	@Positive
	private Integer amount;

	public Lottery(){


	}

	public Lottery(String ticketid, Integer price, Integer amount) {
		this.ticketid = ticketid;
		this.price = price;
		this.amount = amount;
	}

}