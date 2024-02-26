package com.kbtg.bootcamp.posttest.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
public record createLotteryRequest(
      @NotNull
              @Size(min = 6, max = 6, message = "lotteryNumber must be 6 digits")
      String ticket,

      @NotNull
              @Positive
      Integer price,

      @NotNull
      @Positive
      Integer amount

){ }
