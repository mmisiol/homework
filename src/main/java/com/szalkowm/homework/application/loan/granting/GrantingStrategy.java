package com.szalkowm.homework.application.loan.granting;

import com.szalkowm.homework.domain.Loan;
import com.szalkowm.homework.domain.LoanApplication;

public interface GrantingStrategy {
    Loan apply(LoanApplication application);
}
