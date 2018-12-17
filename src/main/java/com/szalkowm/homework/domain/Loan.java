package com.szalkowm.homework.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Loan {

    private Integer id;
    private BigDecimal amount;
    private BigDecimal cost;
    private LocalDate dueDate;

}
