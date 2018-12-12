package com.szalkowm.homework.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Loan {

    private BigDecimal amount;
    private int termInDays;
    private LocalDate dueDate;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getTermInDays() {
        return termInDays;
    }

    public void setTermInDays(int termInDays) {
        this.termInDays = termInDays;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
