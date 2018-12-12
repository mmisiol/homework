package com.szalkowm.homework.application;

import com.szalkowm.homework.domain.Loan;

import java.util.Collection;

public class LoanFetcher {

    private final LoanRepository repository;

    public LoanFetcher(LoanRepository loanRepository) {
        this.repository = loanRepository;
    }

    public Collection<Loan> getAllLoans() {
        return this.repository.getAll();
    }
}
