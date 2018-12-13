package com.szalkowm.homework.infrastructure.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalTime;

@Configuration
@Getter
@Setter
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "rules.fraud-detection.amount-in-time-range")
public class FraudRulesProperties {

    private BigDecimal amount;
    private LocalTime from;
    private LocalTime to;

    public void setFrom(String from) {
        this.from = LocalTime.parse(from);
    }

    public void setTo(String to) {
        this.to = LocalTime.parse(to);
    }
}
