package com.szalkowm.homework.infrastructure.configuration.beans;

import com.szalkowm.homework.application.rule.business.AmountRange;
import com.szalkowm.homework.application.rule.business.TermRange;
import com.szalkowm.homework.infrastructure.configuration.property.BusinessRulesProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessRulesBeans {

    @Bean
    public AmountRange amountRange(BusinessRulesProperties businessRulesProperties) {
        return new AmountRange(
                businessRulesProperties.getMinAmount(),
                businessRulesProperties.getMaxAmount()
        );
    }

    @Bean
    public TermRange termRange(BusinessRulesProperties businessRulesProperties) {
        return new TermRange(
                businessRulesProperties.getMinTerm(),
                businessRulesProperties.getMaxTerm()
        );
    }
}
