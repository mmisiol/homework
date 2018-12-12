package com.szalkowm.homework.application.validation;

import com.szalkowm.homework.domain.LoanApplication;

public interface ValidationRule {
    void run(LoanApplication loanApplication);
}
