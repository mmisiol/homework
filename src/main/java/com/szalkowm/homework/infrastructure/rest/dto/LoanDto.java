package com.szalkowm.homework.infrastructure.rest.dto;

import java.math.BigDecimal;

public class LoanDto {

    private Long id;
    private BigDecimal amount;
    private Integer term;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }
}
