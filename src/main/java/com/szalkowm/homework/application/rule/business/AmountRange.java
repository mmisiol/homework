package com.szalkowm.homework.application.rule.business;

import com.szalkowm.homework.application.rule.Rule;
import com.szalkowm.homework.domain.LoanApplication;

import java.math.BigDecimal;

import static java.lang.String.format;

public class AmountRange implements Rule<LoanApplication> {

    private static final String MSG_PATTERN = "Amount has to be between %s and %s.";

    private final BigDecimal minAmount;
    private final BigDecimal maxAmount;

    public AmountRange(BigDecimal minAmount, BigDecimal maxAmount) {
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    @Override
    public void execute(LoanApplication loanApplication) {
        hasAmount(loanApplication.getAmount());
        amountInRange(loanApplication.getAmount());
    }

    private void hasAmount(BigDecimal amount) throws BusinessRuleViolationException {
        if (amount == null) {
            throw new BusinessRuleViolationException("Application has no amount specified");
        }
    }

    private void amountInRange(BigDecimal amount) throws BusinessRuleViolationException {
        if (this.maxAmount.compareTo(amount) < 0 || this.minAmount.compareTo(amount) > 0) {
            throw new BusinessRuleViolationException(format(MSG_PATTERN, this.minAmount, this.maxAmount));
        }
    }
}
