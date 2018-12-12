package com.szalkowm.homework.infrastructure.rest;

import com.szalkowm.homework.infrastructure.rest.dto.LoanDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class LoanController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/loans")
    public LoanDto greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        LoanDto loanDto = new LoanDto();
        loanDto.setId(counter.getAndIncrement());
        loanDto.setAmount(new BigDecimal("5.0"));
        loanDto.setTerm(10);


        return loanDto;
    }
}
