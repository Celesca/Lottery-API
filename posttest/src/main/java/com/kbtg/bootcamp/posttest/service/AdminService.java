package com.kbtg.bootcamp.posttest.service;

import com.kbtg.bootcamp.posttest.controller.createLotteryRequest;
import com.kbtg.bootcamp.posttest.exception.BadRequestException;
import com.kbtg.bootcamp.posttest.model.Lottery;
import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final LotteryRepository lotteryRepository;

    public AdminService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    @Transactional
    public String createLottery(createLotteryRequest request) {

        // Check the characters
        if (request.ticket().length() != 6) {
            throw new BadRequestException("lotteryNumber must be 6 digits");
        }

        // Check the price
        if (request.price() <= 0) {
            throw new BadRequestException("price must be positive");
        }

        // Check the amount
        if (request.amount() <= 0) {
            throw new BadRequestException("amount must be positive");
        }

        // Check if the lottery number already exists
        if (lotteryRepository.existsByTicketid(request.ticket())) {
            Lottery lottery = lotteryRepository.findByTicketid(request.ticket());
            lottery.setAmount(lottery.getAmount() + request.amount());
            lotteryRepository.save(lottery);
            return "Update lotteries success";
        }
        // If the lottery number does not exist, create a new lottery
        else {
            Lottery newLottery = new Lottery(request.ticket(), request.price(), request.amount());
            lotteryRepository.save(newLottery);
            return "Create lotteries success";
        }

    }
}