package com.youtube.danvega.content_calendar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		//Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
	}
	
	/* either use this method with the @Bean annotation or the DataLoader class for bootstrapping the application.
	* The method runs after all dependency injection has occurred. The same happens with the DataLoader class. */
//	@Bean
//	CommandLineRunner commandLineRunner() {
//		return args -> {
//			System.out.println("Application started...");
//		};
//	}

}