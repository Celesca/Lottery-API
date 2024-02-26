package com.kbtg.bootcamp.posttest.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserBuyLotteriesResponse {

    public Integer id;

    public UserBuyLotteriesResponse(Integer transactionId) {
        setId(transactionId);
    }

}
