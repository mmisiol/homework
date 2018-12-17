package com.szalkowm.homework.infrastructure.configuration.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@Getter
@Setter
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "rules.business")
public class BusinessRulesProperties {
    private BigDecimal maxAmount;
    private BigDecimal minAmount;
    private Integer minTerm;
    private Integer maxTerm;
}
