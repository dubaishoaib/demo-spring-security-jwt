package com.example.demo.spring.security.jwt.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.example.demo.spring.security.jwt.repo")
@EntityScan("com.example.demo.spring.security.jwt.domain")
public class ApplicationConfig {
}
