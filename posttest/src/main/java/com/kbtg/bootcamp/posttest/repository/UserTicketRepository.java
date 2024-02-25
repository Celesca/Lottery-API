package com.kbtg.bootcamp.posttest.repository;

import com.kbtg.bootcamp.posttest.model.UserTicket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserTicketRepository extends JpaRepository<UserTicket, Integer> {

    @Transactional
    @Query("SELECT t FROM UserTicket t WHERE t.userid = ?1")
    List<UserTicket> findByUserId(Integer userId);
}