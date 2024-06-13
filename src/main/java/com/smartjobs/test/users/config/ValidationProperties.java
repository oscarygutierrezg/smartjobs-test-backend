package com.smartjobs.test.users.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.validation")
public class ValidationProperties {

    private String passwordPattern;

    // Getter and Setter

    public String getEmailPattern() {
        return passwordPattern;
    }

    public void setEmailPattern(String passwordPattern) {
        this.passwordPattern = passwordPattern;
    }
}
