package com.kbtg.bootcamp.posttest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "Lottery")
public class Lottery {

    @Id
    @NotNull
    @Size(min = 6, max = 6, message = "lotteryNumber must be 6 digits")
    private String ticketid;

    @NotNull
    @Positive
    private Integer price;

    @NotNull
    private Integer amount;

    public Lottery(){


    }

    public Lottery(String ticketid, Integer price, Integer amount) {
        this.ticketid = ticketid;
        this.price = price;
        this.amount = amount;
    }

    public String getTicketid() {
        return ticketid;
    }

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}