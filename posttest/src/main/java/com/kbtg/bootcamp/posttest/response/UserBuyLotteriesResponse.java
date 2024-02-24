package com.kbtg.bootcamp.posttest.response;

public class UserBuyLotteriesResponse {

    public Integer id;

    public UserBuyLotteriesResponse(Integer transactionId) {
        setId(transactionId);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
