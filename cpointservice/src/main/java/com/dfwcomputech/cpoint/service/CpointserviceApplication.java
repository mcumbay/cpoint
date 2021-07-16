package com.dfwcomputech.cpoint.service;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.dfwcomputech.cpoint.common.util.BeanValidator;
import com.dfwcomputech.cpoint.integration.CpointintegrationApplication;

@SpringBootApplication
@Import(CpointintegrationApplication.class)
public class CpointserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CpointserviceApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Bean
	public BeanValidator beanValidatior() {
		return new BeanValidator();
	}

}
