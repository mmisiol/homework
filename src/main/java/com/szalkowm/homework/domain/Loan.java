package com.szalkowm.homework.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Loan {

    private Integer id;
    private BigDecimal amount;
    private BigDecimal cost;
    private LocalDate dueDate;

}
