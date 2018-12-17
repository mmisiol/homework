package com.szalkowm.homework.application.loan.extension;

import com.szalkowm.homework.application.loan.LoanFetcher;
import com.szalkowm.homework.application.loan.LoanRepository;
import com.szalkowm.homework.domain.Loan;

public class FixedTermExtender implements LoanExtender {

    private final LoanFetcher loanFetcher;
    private final LoanRepository loanRepository;
    private final Integer extensionDays;

    public FixedTermExtender(LoanFetcher loanFetcher, LoanRepository loanRepository, Integer extensionDays) {
        this.loanFetcher = loanFetcher;
        this.loanRepository = loanRepository;
        this.extensionDays = extensionDays;
    }

    @Override
    public void extend(Integer loanId) {
        Loan loan = loanFetcher.get(loanId);
        loan.setDueDate(loan.getDueDate().plusDays(this.extensionDays));
        loanRepository.update(loan);
    }
}
