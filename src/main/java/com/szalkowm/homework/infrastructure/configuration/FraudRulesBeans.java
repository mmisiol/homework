package com.szalkowm.homework.infrastructure.configuration;

import com.szalkowm.homework.application.rule.fraud.AmountInTimeRange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FraudRulesBeans {


    @Bean
    public AmountInTimeRange amountInTimeRange(FraudRulesProperties fraudRulesProperties) {
        return new AmountInTimeRange(
                fraudRulesProperties.getAmount(),
                fraudRulesProperties.getFrom(),
                fraudRulesProperties.getTo());
    }
}
