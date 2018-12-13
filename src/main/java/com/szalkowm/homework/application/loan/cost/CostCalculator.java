package com.szalkowm.homework.application.loan.cost;

import com.szalkowm.homework.domain.LoanApplication;

import java.math.BigDecimal;

public interface CostCalculator {

    BigDecimal calculateCost(LoanApplication application);

}
