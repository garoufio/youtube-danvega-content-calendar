package com.youtube.danvega.content_calendar.config;

import com.youtube.danvega.content_calendar.model.Content;
import com.youtube.danvega.content_calendar.model.ContentStatus;
import com.youtube.danvega.content_calendar.model.ContentType;
import com.youtube.danvega.content_calendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Profile("dev") // can use ! e.g. !prod
@Component
public class DataLoader implements CommandLineRunner {
  
  ContentRepository repository;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public DataLoader(ContentRepository repository) {
    this.repository = repository;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  //@Override
  public void run(String... args) throws Exception {
    System.out.println("DataLoader running...");
    // Content content = new Content(
    //     null,
    //     "Hello Claude",
    //     "An introductory video about Claude, the AI assistant from Anthropic.",
    //     ContentStatus.IDEA,
    //     ContentType.VIDEO,
    //     LocalDateTime.now(),
    //     null,
    //     ""
    // );
    // repository.save(content);
    //System.out.println("Content saved: " + content);
  }
  
}
