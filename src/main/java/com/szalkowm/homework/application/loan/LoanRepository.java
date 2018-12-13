package com.szalkowm.homework.application.loan;

import com.szalkowm.homework.domain.Loan;

import java.util.Collection;

public interface LoanRepository {

    Integer add(Loan loan);

    void update(Loan loan);

    Collection<Loan> getAll();
}
