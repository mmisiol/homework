package com.szalkowm.homework.infrastructure.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LoanDto {

    private Integer id;
    private BigDecimal amount;
    private LocalDate dueDate;

}
