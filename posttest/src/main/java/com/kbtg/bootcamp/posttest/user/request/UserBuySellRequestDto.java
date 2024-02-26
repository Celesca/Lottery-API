package com.kbtg.bootcamp.posttest.user.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserBuySellRequestDto {

    @NotNull
    @Size (min = 10, max = 10, message = "userId must be 10 digits")
    private  String userId;

    @NotNull
    @Size (min = 6, max = 6, message = "ticketId must be 6 digits")
    private  String ticketId;
    public UserBuySellRequestDto() {
    }

    public UserBuySellRequestDto(String userId, String ticketId) {
        this.userId = userId;
        this.ticketId = ticketId;
    }
}
