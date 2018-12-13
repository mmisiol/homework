package com.szalkowm.homework.application.loan.cost;

import com.szalkowm.homework.domain.LoanApplication;

import java.math.BigDecimal;
import java.util.Objects;

public class PrincipalPercentage implements CostCalculator {

    private final BigDecimal percentage;

    public PrincipalPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    @Override
    public BigDecimal calculateCost(LoanApplication application) {
        BigDecimal principal = application.getAmount();
        Objects.requireNonNull(principal);
        return principal.divide(percentage);
    }
}
