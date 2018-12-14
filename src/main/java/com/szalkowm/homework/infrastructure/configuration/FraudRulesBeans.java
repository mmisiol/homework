package com.szalkowm.homework.infrastructure.configuration;

import com.szalkowm.homework.application.rule.fraud.AmountInTimeRange;
import com.szalkowm.homework.application.time.TimeRange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FraudRulesBeans {


    @Bean
    public AmountInTimeRange amountInTimeRange(FraudRulesProperties fraudRulesProperties, TimeRange timeRange) {
        return new AmountInTimeRange(fraudRulesProperties.getAmount(), timeRange);
    }

    @Bean
    TimeRange timeRange(FraudRulesProperties fraudRulesProperties) {
        return new TimeRange(fraudRulesProperties.getFrom(), fraudRulesProperties.getTo());
    }
}
