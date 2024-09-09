package com.demo.hrms;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.demo.hrms.config.SecurityConfig;

@SpringBootApplication
@Import(SecurityConfig.class)
public class HrmsDemoApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(HrmsDemoApplication.class, args);
	}
}
