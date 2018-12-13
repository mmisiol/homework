package com.szalkowm.homework.application.loan;

import com.szalkowm.homework.application.loan.cost.CostCalculator;
import com.szalkowm.homework.application.rule.Rule;
import com.szalkowm.homework.application.rule.business.BusinessRuleViolationException;
import com.szalkowm.homework.domain.Loan;
import com.szalkowm.homework.domain.LoanApplication;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

public class LoanGranter {

    private final LoanRepository repository;
    private final CostCalculator costCalculator;

    @Setter
    private Collection<Rule<LoanApplication>> rules = Collections.emptyList();

    public LoanGranter(LoanRepository repository, CostCalculator costCalculator) {
        this.repository = repository;
        this.costCalculator = costCalculator;
    }

    public Loan apply(LoanApplication application) throws BusinessRuleViolationException {
        runRules(application);
        return createLoan(application);
    }

    private Loan createLoan(LoanApplication application) {
        Loan loan = new Loan();
        loan.setAmount(application.getAmount());
        loan.setDueDate(LocalDate.now().plusDays(((long) application.getTermInDays())));
        loan.setCost(this.costCalculator.calculateCost(application));

        loan.setId(this.repository.add(loan));
        return loan;
    }

    private void runRules(LoanApplication application) throws BusinessRuleViolationException {
        for (Rule<LoanApplication> rule : this.rules) {
            rule.execute(application);
        }
    }
}
