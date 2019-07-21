package com.alexislavie.coding.assignment.becare.configuration.application;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties("application")
@Validated
public class ApplicationConfiguration {

    @NestedConfigurationProperty
    private ApplicationApiConfiguration api;
}
