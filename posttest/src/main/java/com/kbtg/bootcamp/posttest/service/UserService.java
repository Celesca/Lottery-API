package com.kbtg.bootcamp.posttest.service;

import com.kbtg.bootcamp.posttest.model.Lottery;
import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final LotteryRepository lotteryRepository;


    public UserService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    // Get all lotteries
    public List<Lottery> getAllLotteries() {
        List<Lottery> lotteries = lotteryRepository.findAll();
        if (lotteries.isEmpty()) {
            return null;
        }
        return lotteries;
    }

    // Buy lotteries

}