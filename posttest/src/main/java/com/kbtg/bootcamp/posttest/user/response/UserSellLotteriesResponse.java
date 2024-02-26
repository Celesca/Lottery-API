package com.kbtg.bootcamp.posttest.user.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSellLotteriesResponse {
    private String ticket;

    public UserSellLotteriesResponse(String ticket) {
        setTicket(ticket);
    }

}