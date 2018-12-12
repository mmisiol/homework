package com.szalkowm.homework.application.rule.business;

import com.szalkowm.homework.application.rule.Rule;
import com.szalkowm.homework.domain.LoanApplication;

import java.math.BigDecimal;

import static java.lang.String.format;

public class MaxAmount implements Rule<LoanApplication> {

    private final BigDecimal maxAmount;

    public MaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public void execute(LoanApplication loanApplication) {
        hasAmount(loanApplication.getAmount());
        amountLessOrEqualMax(loanApplication.getAmount());
    }

    private void hasAmount(BigDecimal amount) throws BusinessRuleViolationException {
        if (amount == null) {
            throw new BusinessRuleViolationException("Application has no amount specified");
        }
    }

    private void amountLessOrEqualMax(BigDecimal amount) throws BusinessRuleViolationException {
        if (this.maxAmount.compareTo(amount) < 0) {
            throw new BusinessRuleViolationException(format("Max amount %s exceeded", this.maxAmount));
        }
    }
}
