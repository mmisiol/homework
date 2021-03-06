package com.szalkowm.homework.infrastructure.repository;

import com.szalkowm.homework.application.loan.LoanRepository;
import com.szalkowm.homework.domain.Loan;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class LocalLoanRepository implements LoanRepository {

    private final Map<Integer, Loan> idToLoan = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public Integer add(Loan loan) {
        Integer newLoanId = nextId.getAndIncrement();
        loan.setId(newLoanId);
        this.idToLoan.put(newLoanId, loan);
        return newLoanId;
    }

    @Override
    public void update(Loan loan) {
        this.idToLoan.put(loan.getId(), loan);
    }

    @Override
    public Loan get(Integer loanId) {
        return this.idToLoan.get(loanId);
    }
}
