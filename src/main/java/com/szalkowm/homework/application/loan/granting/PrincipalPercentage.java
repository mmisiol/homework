package com.szalkowm.homework.application.loan.granting;

import com.szalkowm.homework.application.loan.LoanRepository;
import com.szalkowm.homework.application.rule.Rule;
import com.szalkowm.homework.application.rule.business.BusinessRuleViolationException;
import com.szalkowm.homework.domain.Loan;
import com.szalkowm.homework.domain.LoanApplication;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class PrincipalPercentage implements GrantingStrategy {

    private final LoanRepository repository;
    private final BigDecimal percentage;

    @Setter
    private Collection<Rule<LoanApplication>> rules = Collections.emptyList();

    public PrincipalPercentage(LoanRepository repository, BigDecimal percentage) {
        this.repository = repository;
        this.percentage = percentage;
    }

    @Override
    public Loan apply(LoanApplication application) throws BusinessRuleViolationException {
        runRules(application);
        return createLoan(application);
    }

    private Loan createLoan(LoanApplication application) {
        Loan loan = new Loan();
        loan.setAmount(application.getAmount());
        loan.setDueDate(LocalDate.now().plusDays(((long) application.getTermInDays())));
        loan.setCost(calculateCost(application));

        loan.setId(this.repository.add(loan));
        return loan;
    }

    private BigDecimal calculateCost(LoanApplication application) {
        BigDecimal principal = application.getAmount();
        Objects.requireNonNull(principal);
        return principal.multiply(this.percentage);
    }

    private void runRules(LoanApplication application) throws BusinessRuleViolationException {
        for (Rule<LoanApplication> rule : this.rules) {
            rule.execute(application);
        }
    }

}
