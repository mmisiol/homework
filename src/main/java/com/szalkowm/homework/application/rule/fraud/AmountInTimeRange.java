package com.szalkowm.homework.application.rule.fraud;

import com.szalkowm.homework.application.rule.Rule;
import com.szalkowm.homework.application.time.TimeRange;
import com.szalkowm.homework.domain.LoanApplication;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Objects;

public class AmountInTimeRange implements Rule<LoanApplication> {

    private final static String ERROR_MSG = "Max amount loans are not allowed at this time";

    private final BigDecimal amount;
    private final TimeRange timeRange;

    public AmountInTimeRange(BigDecimal amount, TimeRange timeRange) {
        this.amount = amount;
        this.timeRange = timeRange;
    }

    @Override
    public void execute(LoanApplication input) {
        BigDecimal amount = input.getAmount();
        Objects.requireNonNull(amount);
        if (amountEqual(amount) && inTimeRange()) {
            throw new FraudDetectionException(ERROR_MSG);
        }
    }

    private boolean amountEqual(BigDecimal amount) {
        return this.amount.compareTo(amount) == 0;//can't use equals cause of scale check
    }

    private boolean inTimeRange() {
        return this.timeRange.isInRange(LocalTime.now());
    }
}
