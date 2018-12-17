package com.szalkowm.homework.infrastructure.rest.controller;

import com.szalkowm.homework.application.loan.LoanExtender;
import com.szalkowm.homework.application.loan.LoanFetcher;
import com.szalkowm.homework.application.loan.granting.GrantingStrategy;
import com.szalkowm.homework.application.rule.business.BusinessRuleViolationException;
import com.szalkowm.homework.domain.Loan;
import com.szalkowm.homework.domain.LoanApplication;
import com.szalkowm.homework.infrastructure.rest.dto.LoanApplicationDto;
import com.szalkowm.homework.infrastructure.rest.dto.LoanDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanController {

    private final LoanFetcher loanFetcher;
    private final GrantingStrategy grantingStrategy;
    private final LoanExtender loanExtender;

    public LoanController(LoanFetcher loanFetcher, GrantingStrategy grantingStrategy, LoanExtender loanExtender) {
        this.loanFetcher = loanFetcher;
        this.grantingStrategy = grantingStrategy;
        this.loanExtender = loanExtender;
    }

    @RequestMapping("/loans/{loanId}")
    public LoanDto getLoan(@PathVariable Integer loanId) {
        return convertToDto(loanFetcher.get(loanId));
    }

    private LoanDto convertToDto(Loan loan) {
        LoanDto dto = new LoanDto();
        dto.setAmount(loan.getAmount());
        dto.setDueDate(loan.getDueDate());
        dto.setCost(loan.getCost());
        dto.setId(loan.getId());
        return dto;
    }

    @RequestMapping(value = "/loans", method = RequestMethod.POST)
    public LoanDto apply(@RequestBody LoanApplicationDto loanApplication) throws BusinessRuleViolationException {
        Loan loan = this.grantingStrategy.apply(convertToEntity(loanApplication));
        return convertToDto(loan);
    }

    private LoanApplication convertToEntity(LoanApplicationDto applicationDto) {
        LoanApplication entity = new LoanApplication();
        entity.setAmount(applicationDto.getAmount());
        entity.setTermInDays(applicationDto.getTermInDays());
        return entity;
    }

    @RequestMapping(value = "/loans/{loanId}/extend", method = RequestMethod.POST)
    public void extend(@PathVariable Integer loanId) throws BusinessRuleViolationException {
        loanExtender.extend(loanId);
    }
}
