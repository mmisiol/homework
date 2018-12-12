package com.szalkowm.homework.infrastructure.rest.controller;

import com.szalkowm.homework.application.LoanFetcher;
import com.szalkowm.homework.application.LoanGranter;
import com.szalkowm.homework.domain.Loan;
import com.szalkowm.homework.domain.LoanApplication;
import com.szalkowm.homework.infrastructure.rest.dto.LoanApplicationDto;
import com.szalkowm.homework.infrastructure.rest.dto.LoanDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class LoanController {

    private final LoanFetcher loanFetcher;
    private final LoanGranter loanGranter;

    public LoanController(LoanFetcher loanFetcher, LoanGranter loanGranter) {
        this.loanFetcher = loanFetcher;
        this.loanGranter = loanGranter;
    }

    @RequestMapping("/loans")
    public Collection<LoanDto> getLoans() {
        return loanFetcher.getAllLoans().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private LoanDto convertToDto(Loan loan) {
        LoanDto dto = new LoanDto();
        dto.setAmount(loan.getAmount());
        dto.setDueDate(loan.getDueDate());
        dto.setId(loan.getId());
        return dto;
    }

    @RequestMapping(value = "/loans", method = RequestMethod.POST)
    public LoanDto apply(@RequestBody LoanApplicationDto loanApplication) {
        Loan loan = loanGranter.apply(convertToEntity(loanApplication));
        return convertToDto(loan);
    }

    private LoanApplication convertToEntity(LoanApplicationDto applicationDto) {
        LoanApplication entity = new LoanApplication();
        entity.setAmount(applicationDto.getAmount());
        entity.setTermInDays(applicationDto.getTermInDays());
        return entity;
    }
}
