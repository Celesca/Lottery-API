package com.kbtg.bootcamp.posttest.service;

import com.kbtg.bootcamp.posttest.model.Lottery;
import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
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
    public List<String> getAllLotteries() {

        // find all ticket id
        List<Lottery> lotteries = lotteryRepository.findAll();

        if (lotteries.isEmpty()) {
            return null;
        }

        List<String> ticketIds = new ArrayList<>();

        lotteries.forEach(lottery -> {
            ticketIds.add(lottery.getTicketid());
        });

        return ticketIds;

    }

    // Buy lotteries


}