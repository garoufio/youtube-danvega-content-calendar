package com.youtube.danvega.content_calendar;

import com.youtube.danvega.content_calendar.config.ContentCalendarProperties;
import com.youtube.danvega.content_calendar.model.Content;
import com.youtube.danvega.content_calendar.model.ContentStatus;
import com.youtube.danvega.content_calendar.model.ContentType;
import com.youtube.danvega.content_calendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@EnableConfigurationProperties(ContentCalendarProperties.class)
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		//Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
	}
	
	/* either use this method with the @Bean annotation or the DataLoader class for bootstrapping the application.
	* The method runs after all dependency injection has occurred. The same happens with the DataLoader class. */
//	@Bean
//	CommandLineRunner commandLineRunner(ContentRepository repository) {
//		return args -> {
//			System.out.println("Application started...");
//			System.out.println("DataLoader running...");
//			Content content = new Content(
//					1,
//					"Hello Claude",
//					"An introductory video about Claude, the AI assistant from Anthropic.",
//					ContentStatus.IDEA,
//					ContentType.VIDEO,
//					LocalDateTime.now(),
//					null,
//					""
//			);
//			//repository.save(content);
//			System.out.println("Content saved: " + content);
//		};
//	}

}