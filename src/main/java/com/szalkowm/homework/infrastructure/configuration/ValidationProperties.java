package com.szalkowm.homework.infrastructure.configuration;

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
@ConfigurationProperties(prefix = "validation")
public class ValidationProperties {

    private BigDecimal maxAmount;
}
