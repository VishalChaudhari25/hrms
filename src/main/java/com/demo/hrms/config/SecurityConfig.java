package com.demo.hrms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
						.permitAll().anyRequest().authenticated());

		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
