package com.szalkowm.homework.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class LoanApplication {
    private BigDecimal amount;
    private int termInDays;
}
