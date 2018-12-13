package com.szalkowm.homework.application.loan;

import com.szalkowm.homework.domain.Loan;

public class LoanFetcher {

    private final LoanRepository repository;

    public LoanFetcher(LoanRepository loanRepository) {
        this.repository = loanRepository;
    }

    public Loan get(Integer loanId) {
        Loan loan = this.repository.get(loanId);
        if (loan == null) {
            throw new LoanNotFoundException("Can't find loan with id:" + loanId);
        }
        return loan;
    }
}
