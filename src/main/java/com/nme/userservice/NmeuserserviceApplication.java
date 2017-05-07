package com.nme.userservice;

import com.nme.userservice.application.DevelopmentProfileConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:nmeuserservice.properties")
public class NmeuserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(
				new Object[]{
						NmeuserserviceApplication.class ,
						DevelopmentProfileConfiguration.class
				}
				, args);
	}
}
