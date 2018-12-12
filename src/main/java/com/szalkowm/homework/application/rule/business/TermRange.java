package com.szalkowm.homework.application.rule.business;

import com.szalkowm.homework.application.rule.Rule;
import com.szalkowm.homework.domain.LoanApplication;

import static java.lang.String.format;

public class TermRange implements Rule<LoanApplication> {

    private final Integer minTerm;
    private final Integer maxTerm;

    public TermRange(Integer minTerm, Integer maxTerm) {
        this.minTerm = minTerm;
        this.maxTerm = maxTerm;
    }

    @Override
    public void execute(LoanApplication application) {
        hasTerm(application.getTermInDays());
        termWithinRange(application.getTermInDays());
    }

    private void hasTerm(Integer termInDays) {
        if (termInDays == null) {
            throw new BusinessRuleViolationException("Application has no term specified");
        }
    }

    private void termWithinRange(Integer termInDays) {
        if (termInDays > this.maxTerm || termInDays < this.minTerm) {
            throw new BusinessRuleViolationException(format("Term as to be between %d and %d", this.minTerm, this.maxTerm));
        }
    }
}
