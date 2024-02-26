package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import com.kbtg.bootcamp.posttest.model.Lottery;
import com.kbtg.bootcamp.posttest.model.UserTicket;
import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.repository.UserTicketRepository;
import com.kbtg.bootcamp.posttest.response.UserBuyLotteriesResponse;
import com.kbtg.bootcamp.posttest.response.UserGetAllLotteriesResponse;
import com.kbtg.bootcamp.posttest.response.UserGetMyLotteriesResponse;
import com.kbtg.bootcamp.posttest.response.UserSellLotteriesResponse;
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

        lotteries.forEach(lottery -> ticketIds.add(lottery.getTicketid()));

        UserGetAllLotteriesResponse response = new UserGetAllLotteriesResponse();
        response.setTickets(ticketIds);

        return response;

    }

    // Buy lotteries
    @Transactional
    public UserBuyLotteriesResponse buyLotteries(Integer userId, Integer ticketId) {

        Lottery lottery = lotteryRepository.findByTicketid(ticketId.toString());
        if (lottery == null) {
            throw new NotFoundException("Ticket not found");
        }

        UserTicket transaction = new UserTicket(userId, ticketId.toString(), lottery.getPrice());
        userTicketRepository.save(transaction);

        lottery.setAmount(lottery.getAmount() - 1);
        if (lottery.getAmount() <= 0) {
            lotteryRepository.delete(lottery);
        }

        return new UserBuyLotteriesResponse(transaction.getId());
    }

    // Get user lotteries
    @Transactional
    public UserGetMyLotteriesResponse getMyLotteries(Integer userId) {

        List<UserTicket> userTickets = userTicketRepository.findByUserId(userId);

        if (userTickets.isEmpty()) {
            throw  new NotFoundException("User does not have any ticket or not found user id");
        }

        List<String> currentuserTickets = new ArrayList<>();
        userTickets.forEach(userTicket -> currentuserTickets.add(userTicket.getTicketid()));

        Integer totalAmount = 0;
        for (UserTicket userTicket : userTickets) {
            totalAmount += userTicket.getPrice();
        }

        UserGetMyLotteriesResponse response = new UserGetMyLotteriesResponse();
        response.setTickets(currentuserTickets);
        response.setCost(totalAmount);
        response.setCount(currentuserTickets.size());

        return response;
    }

    // Sell back my lottery ticket
    @Transactional
    public UserSellLotteriesResponse sellLotteries(Integer userId, String ticketId) {
        // find ticket id
        UserTicket userTicket = userTicketRepository.findByUserIdAndTicketId(userId, ticketId);
        if (userTicket == null) {
            throw new NotFoundException("Ticket not found in user ticket list");
        }

        userTicketRepository.delete(userTicket);

        return new UserSellLotteriesResponse(ticketId);
    }

}