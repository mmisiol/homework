package com.szalkowm.homework.infrastructure.configuration;

import com.szalkowm.homework.application.rule.business.MaxAmount;
import com.szalkowm.homework.application.rule.business.TermRange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessRulesBeans {

    @Bean
    public MaxAmount maxAmount(BusinessRulesProperties businessRulesProperties) {
        return new MaxAmount(businessRulesProperties.getMaxAmount());
    }

    @Bean
    public TermRange termRange(BusinessRulesProperties businessRulesProperties) {
        return new TermRange(
                businessRulesProperties.getMinTerm(),
                businessRulesProperties.getMaxTerm()
        );
    }
}
