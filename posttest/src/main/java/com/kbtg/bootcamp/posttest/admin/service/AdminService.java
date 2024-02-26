package com.kbtg.bootcamp.posttest.admin.service;


import com.kbtg.bootcamp.posttest.admin.request.createLotteryRequestDto;
import com.kbtg.bootcamp.posttest.admin.response.createLotteryResponse;
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
    public createLotteryResponse createLottery(createLotteryRequestDto request) {

        String requestTicket = request.ticket();

        // Check if the lottery number already exists
        if (lotteryRepository.existsByTicketid(requestTicket)) {

            Lottery lottery = lotteryRepository.findByTicketid(requestTicket);
            lottery.setAmount(lottery.getAmount() + request.amount());
            lotteryRepository.save(lottery);

        }

        // If the lottery number does not exist, create a new lottery
        else {
            Lottery newLottery = new Lottery(requestTicket, request.price(), request.amount());
            lotteryRepository.save(newLottery);

        }
        return new createLotteryResponse(requestTicket);

    }
}