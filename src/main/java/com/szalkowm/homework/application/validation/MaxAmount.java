package com.szalkowm.homework.application.validation;

import com.szalkowm.homework.domain.LoanApplication;

import java.math.BigDecimal;

import static java.lang.String.format;

public class MaxAmount implements ValidationRule {

    private final BigDecimal maxAmount;

    public MaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public void run(LoanApplication loanApplication) {
        hasAmount(loanApplication.getAmount());
        amountLessOrEqualMax(loanApplication.getAmount());
    }

    private void hasAmount(BigDecimal amount) throws ApplicationValidationException {
        if (amount == null) {
            throw new ApplicationValidationException("Application has no amount specified");
        }
    }

    private void amountLessOrEqualMax(BigDecimal amount) throws ApplicationValidationException {
        if (this.maxAmount.compareTo(amount) < 0) {
            throw new ApplicationValidationException(format("Max amount %s exceeded", this.maxAmount));
        }
    }
}
