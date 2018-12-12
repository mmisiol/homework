package com.szalkowm.homework.application;

import com.szalkowm.homework.domain.Loan;
import com.szalkowm.homework.domain.LoanApplication;

import java.time.LocalDate;

public class LoanGranter {

    private final LoanRepository repository;

    public LoanGranter(LoanRepository repository) {
        this.repository = repository;
    }

    public Loan apply(LoanApplication application) {
        Loan loan = new Loan();
        loan.setAmount(application.getAmount());
        loan.setDueDate(LocalDate.now().plusDays(((long) application.getTermInDays())));
        loan.setId(this.repository.add(loan));
        return loan;
    }
}
