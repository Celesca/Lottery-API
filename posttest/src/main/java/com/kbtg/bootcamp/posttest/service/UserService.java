package com.kbtg.bootcamp.posttest.service;

import com.kbtg.bootcamp.posttest.model.Lottery;
import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.response.UserGetAllLotteriesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final LotteryRepository lotteryRepository;


    public UserService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    // Get all lotteries
    public ResponseEntity<UserGetAllLotteriesResponse> getAllLotteries() {

        // find all ticket id
        List<Lottery> lotteries = lotteryRepository.findAll();

        if (lotteries.isEmpty()) {
            return null;
        }

        List<String> ticketIds = new ArrayList<>();

        lotteries.forEach(lottery -> {
            ticketIds.add(lottery.getTicketid());
        });

        UserGetAllLotteriesResponse response = new UserGetAllLotteriesResponse();
        response.setTickets(ticketIds);

        return ResponseEntity.ok(response);

    }

    // Buy lotteries



}