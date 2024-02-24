package com.kbtg.bootcamp.posttest.repository;



import com.kbtg.bootcamp.posttest.model.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotteryRepository extends JpaRepository<Lottery, Long> {

    boolean existsByTicketid(String ticket);
    
    Lottery findByTicketid(String ticket);

}
