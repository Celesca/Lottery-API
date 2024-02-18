package com.kbtg.bootcamp.posttest.controller;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
record createLotteriesRequest (
      @NotNull
              @Size(min = 6, max = 6, message = "lotteryNumber must be 6 digits")
      Integer ticket,

      @NotNull
              @Positive
      Integer price,

      @NotNull
      @Positive
      Integer amount

){ }
