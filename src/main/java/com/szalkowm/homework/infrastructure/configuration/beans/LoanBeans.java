package com.szalkowm.homework.infrastructure.configuration.beans;

import com.szalkowm.homework.application.loan.LoanExtender;
import com.szalkowm.homework.application.loan.LoanFetcher;
import com.szalkowm.homework.application.loan.LoanGranter;
import com.szalkowm.homework.application.loan.LoanRepository;
import com.szalkowm.homework.application.loan.cost.CostCalculator;
import com.szalkowm.homework.application.loan.cost.PrincipalPercentage;
import com.szalkowm.homework.application.rule.Rule;
import com.szalkowm.homework.domain.LoanApplication;
import com.szalkowm.homework.infrastructure.repository.LocalLoanRepository;
import com.szalkowm.homework.infrastructure.rest.controller.LoanController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Collection;

@Configuration
public class LoanBeans {


    @Bean
    public LoanRepository loanRepository() {
        return new LocalLoanRepository();
    }

    @Bean
    public LoanFetcher loanFetcher(LoanRepository loanRepository) {
        return new LoanFetcher(loanRepository);
    }

    @Bean
    public LoanGranter loanGranter(LoanRepository loanRepository,
                                   Collection<Rule<LoanApplication>> rules,
                                   CostCalculator costCalculator) {
        LoanGranter loanGranter = new LoanGranter(loanRepository, costCalculator);
        loanGranter.setRules(rules);
        return loanGranter;
    }

    @Bean
    public CostCalculator costCalculator(@Value("${cost.principalPercentage.percentage}") BigDecimal percentage) {
        return new PrincipalPercentage(percentage);
    }

    @Bean
    public LoanExtender loanExtender(LoanFetcher loanFetcher, LoanRepository loanRepository, @Value("${extension.term}") Integer extensionDays) {
        return new LoanExtender(loanFetcher, loanRepository, extensionDays);
    }

    @Bean
    public LoanController loanController(LoanFetcher loanFetcher, LoanGranter loanGranter, LoanExtender loanExtender) {
        return new LoanController(loanFetcher, loanGranter, loanExtender);
    }
}
