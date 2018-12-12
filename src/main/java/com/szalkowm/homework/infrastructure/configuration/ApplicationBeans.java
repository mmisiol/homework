package com.szalkowm.homework.infrastructure.configuration;

import com.szalkowm.homework.application.LoanFetcher;
import com.szalkowm.homework.application.LoanGranter;
import com.szalkowm.homework.application.LoanRepository;
import com.szalkowm.homework.application.validation.ValidationRule;
import com.szalkowm.homework.infrastructure.repository.LocalLoanRepository;
import com.szalkowm.homework.infrastructure.rest.controller.LoanController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
public class ApplicationBeans {


    @Bean
    public LoanRepository loanRepository() {
        return new LocalLoanRepository();
    }

    @Bean
    public LoanFetcher loanFetcher(LoanRepository loanRepository) {
        return new LoanFetcher(loanRepository);
    }

    @Bean
    public LoanGranter loanGranter(LoanRepository loanRepository, Collection<ValidationRule> validationRules) {
        LoanGranter loanGranter = new LoanGranter(loanRepository);
        loanGranter.setValidationRules(validationRules);
        return loanGranter;
    }

    @Bean
    public LoanController loanController(LoanFetcher loanFetcher, LoanGranter loanGranter) {
        return new LoanController(loanFetcher, loanGranter);
    }


}
