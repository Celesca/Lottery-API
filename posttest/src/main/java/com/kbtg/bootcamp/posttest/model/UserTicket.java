package com.kbtg.bootcamp.posttest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "user_ticket")
public class UserTicket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Size(min = 10, max = 10, message = "userid must be 10 digits")
	private String userid;

	@NotNull
	@Size(min = 6, max = 6, message = "lotteryNumber must be 6 digits")
	@Pattern(regexp = "^[0-9]{6}$", message = "lotteryNumber must be 6 numeric digits")
	private String ticketid;

	@NotNull
	@Positive
	private Integer price;

	public UserTicket() {
	}

	public UserTicket(String userId, String string, Integer ticketPrice) {
		this.userid = userId;
		this.ticketid = string;
		this.price = ticketPrice;
	}

}