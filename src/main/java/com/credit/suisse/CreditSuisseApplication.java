package com.credit.suisse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.credit.suisse.constants.LogConstants;

@SpringBootApplication(scanBasePackages = LogConstants.BASE_PACKAGE)
public class CreditSuisseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditSuisseApplication.class, args);
	}

}
