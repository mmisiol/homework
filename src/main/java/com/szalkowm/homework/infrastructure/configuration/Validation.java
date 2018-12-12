package com.szalkowm.homework.infrastructure.configuration;

import com.szalkowm.homework.application.validation.MaxAmount;
import com.szalkowm.homework.application.validation.ValidationRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

import static java.util.Arrays.asList;

@Configuration
public class Validation {

    @Bean
    public MaxAmount maxAmount(ValidationProperties validationProperties) {
        return new MaxAmount(validationProperties.getMaxAmount());
    }

    @Bean
    public Collection<ValidationRule> validationRules(ValidationRule maxAmount) {
        return asList(maxAmount);
    }
}
