package com.digitalwallet.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Small config to expose BCryptPasswordEncoder as a bean.
 * Separated from SecurityConfig to avoid circular dependencies.
 */
@Configuration
public class PasswordConfig {

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
