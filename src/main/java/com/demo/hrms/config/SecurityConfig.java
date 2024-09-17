package com.demo.hrms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.context.annotation.Bean;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/admin/register", "/admin/login", "/employee/login", "/employee/register/**",
								"/admin/{adminId}/employees", "/employee/{employeeCode}",
								"/admin/{adminId}/employees/{employeeCode}", 
								"/employee/{employeeCode}/checkin", "employee/{employeeCode}/checkout","employee/{employeeCode}/attendance")
						.permitAll().anyRequest().authenticated())
						.sessionManagement(session -> session
					            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Create a session if needed
					            .maximumSessions(1) // Limit users to one session at a time
					            .expiredUrl("/employee/login") // Redirect to login page on session expiration
						);

		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
