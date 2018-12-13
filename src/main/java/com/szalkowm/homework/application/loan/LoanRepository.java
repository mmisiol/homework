package com.szalkowm.homework.application.loan;

import com.szalkowm.homework.domain.Loan;

public interface LoanRepository {

    Integer add(Loan loan);

    void update(Loan loan);

    Loan get(Integer loanId);
}
