package com.szalkowm.homework.infrastructure.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class LoanApplicationDto {

    private BigDecimal amount;
    private int termInDays;

}
