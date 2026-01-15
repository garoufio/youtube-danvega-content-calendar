package com.youtube.danvega.content_calendar.config;

import com.youtube.danvega.content_calendar.model.Content;
import com.youtube.danvega.content_calendar.model.ContentStatus;
import com.youtube.danvega.content_calendar.model.ContentType;
import com.youtube.danvega.content_calendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {
  
  ContentRepository repository;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public DataLoader(ContentRepository repository) {
    this.repository = repository;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public void run(String... args) throws Exception {
    System.out.println("DataLoader running...");
    Content content = new Content(
        null,
        "Hello ChatGPT",
        "An introductory video about ChatGPT",
        ContentStatus.IDEA,
        ContentType.VIDEO,
        LocalDateTime.now(),
        null,
        ""
    );
    repository.save(content);
  }
  
}
