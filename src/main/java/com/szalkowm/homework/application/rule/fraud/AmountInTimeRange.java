package com.szalkowm.homework.application.rule.fraud;

import com.szalkowm.homework.application.rule.Rule;
import com.szalkowm.homework.domain.LoanApplication;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Objects;

public class AmountInTimeRange implements Rule<LoanApplication> {

    private final static String MSG_PATTERN = "Max amount loans are not allowed between %s and %s";

    private final BigDecimal amount;
    private final LocalTime from;
    private final LocalTime to;

    public AmountInTimeRange(BigDecimal amount, LocalTime from, LocalTime to) {
        this.amount = amount;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(LoanApplication input) {
        BigDecimal amount = input.getAmount();
        Objects.requireNonNull(amount);

        if (amountEqual(amount) && inTimeRange()){
            throw new FraudDetectionException(String.format(MSG_PATTERN, this.from, this.to));
        } ;

    }

    private boolean amountEqual(BigDecimal amount) {
        return this.amount.compareTo(amount) == 0;//can't use equals cause of scale check
    }

    private boolean inTimeRange() {
        LocalTime now = LocalTime.now();
        return !now.isBefore(this.from) && !now.isAfter(this.to);
    }
}
