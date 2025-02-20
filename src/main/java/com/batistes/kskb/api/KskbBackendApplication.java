package com.batistes.kskb.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:general-values.properties")
public class KskbBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(KskbBackendApplication.class, args);
	}

}
