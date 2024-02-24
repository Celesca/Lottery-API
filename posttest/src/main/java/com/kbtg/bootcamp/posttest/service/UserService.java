package com.kbtg.bootcamp.posttest.service;

import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import com.kbtg.bootcamp.posttest.model.Lottery;
import com.kbtg.bootcamp.posttest.model.UserTicket;
import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.repository.UserTicketRepository;
import com.kbtg.bootcamp.posttest.response.UserBuyLotteriesResponse;
import com.kbtg.bootcamp.posttest.response.UserGetAllLotteriesResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final LotteryRepository lotteryRepository;
    private final UserTicketRepository userTicketRepository;

    public UserService(LotteryRepository lotteryRepository, UserTicketRepository userTicketRepository) {
        this.lotteryRepository = lotteryRepository;
        this.userTicketRepository = userTicketRepository;
    }

    // Get all lotteries
    @Transactional
    public UserGetAllLotteriesResponse getAllLotteries() {

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

        return response;

    }

    // Buy lotteries
    @Transactional
    public UserBuyLotteriesResponse buyLotteries(Integer userId, Integer ticketId) {
        // find ticket id
        Lottery lottery = lotteryRepository.findByTicketid(ticketId.toString());
        if (lottery == null) {
            throw new NotFoundException("Ticket not found");
        }

        lottery.setAmount(lottery.getAmount() - 1);
        if (lottery.getAmount() <= 0) {
            lotteryRepository.delete(lottery);
        }

        lotteryRepository.save(lottery);

        UserTicket transaction = new UserTicket(userId, ticketId.toString());
        userTicketRepository.save(transaction);

        return new UserBuyLotteriesResponse(transaction.getId());

    }

}