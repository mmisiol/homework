package com.szalkowm.homework.infrastructure.configuration.beans;

import com.szalkowm.homework.application.loan.LoanFetcher;
import com.szalkowm.homework.application.loan.LoanRepository;
import com.szalkowm.homework.application.loan.extension.FixedTermExtender;
import com.szalkowm.homework.application.loan.extension.LoanExtender;
import com.szalkowm.homework.application.loan.granting.GrantingStrategy;
import com.szalkowm.homework.application.loan.granting.PrincipalPercentage;
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
    public PrincipalPercentage grantingStrategy(LoanRepository loanRepository,
                                                Collection<Rule<LoanApplication>> rules,
                                                @Value("${cost.principalPercentage.percentage}") BigDecimal percentage) {
        PrincipalPercentage principalPercentage = new PrincipalPercentage(loanRepository, percentage);
        principalPercentage.setRules(rules);
        return principalPercentage;
    }

    @Bean
    public FixedTermExtender fixedTermExtender(
            LoanFetcher loanFetcher,
            LoanRepository loanRepository,
            @Value("${extension.term}") Integer extensionDays) {
        return new FixedTermExtender(loanFetcher, loanRepository, extensionDays);
    }

    @Bean
    public LoanController loanController(
            LoanFetcher loanFetcher,
            GrantingStrategy grantingStrategy,
            LoanExtender loanExtender) {
        return new LoanController(loanFetcher, grantingStrategy, loanExtender);
    }
}
