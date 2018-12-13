package com.szalkowm.homework.application.loan;

import com.szalkowm.homework.domain.Loan;

public class LoanExtender {

    private final LoanFetcher loanFetcher;
    private final LoanRepository loanRepository;
    private final Integer extensionDays;

    public LoanExtender(LoanFetcher loanFetcher, LoanRepository loanRepository, Integer extensionDays) {
        this.loanFetcher = loanFetcher;
        this.loanRepository = loanRepository;
        this.extensionDays = extensionDays;
    }

    public void extend(Integer loanId) {
        Loan loan = loanFetcher.get(loanId);
        loan.setDueDate(loan.getDueDate().plusDays(this.extensionDays));
        loanRepository.update(loan);
    }
}
