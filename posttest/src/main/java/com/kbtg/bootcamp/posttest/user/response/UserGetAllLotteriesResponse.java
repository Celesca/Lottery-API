package com.kbtg.bootcamp.posttest.user.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserGetAllLotteriesResponse {

    public List<String> tickets;

}