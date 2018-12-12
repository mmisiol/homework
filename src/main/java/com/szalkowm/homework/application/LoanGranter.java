package com.szalkowm.homework.application;

import com.szalkowm.homework.application.validation.ApplicationValidationException;
import com.szalkowm.homework.application.validation.ValidationRule;
import com.szalkowm.homework.domain.Loan;
import com.szalkowm.homework.domain.LoanApplication;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

public class LoanGranter {

    private final LoanRepository repository;

    @Setter
    private Collection<ValidationRule> validationRules = Collections.emptyList();

    public LoanGranter(LoanRepository repository) {
        this.repository = repository;
    }

    public Loan apply(LoanApplication application) throws ApplicationValidationException {
        runValidation(application);
        return createLoan(application);
    }

    private Loan createLoan(LoanApplication application) {
        Loan loan = new Loan();
        loan.setAmount(application.getAmount());
        loan.setDueDate(LocalDate.now().plusDays(((long) application.getTermInDays())));
        loan.setId(this.repository.add(loan));
        return loan;
    }

    private void runValidation(LoanApplication application) throws ApplicationValidationException {
        for (ValidationRule rule : this.validationRules) {
            rule.run(application);
        }
    }
}
