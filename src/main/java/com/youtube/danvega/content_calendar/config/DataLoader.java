package com.youtube.danvega.content_calendar.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.youtube.danvega.content_calendar.model.Content;
import com.youtube.danvega.content_calendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Profile("dev") // can use ! e.g. !prod
@Component
public class DataLoader implements CommandLineRunner {
  
  private final ContentRepository repository;
  private final ObjectMapper objectMapper;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public DataLoader(ContentRepository repository, ObjectMapper objectMapper) {
    this.repository = repository;
    this.objectMapper = objectMapper;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public void run(String... args) throws Exception {
    // load data from json file if no data found
    if (repository.count() > 0) return;
    
    try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/content.json")) {
      if (inputStream == null) {
        throw new IOException("Could not find content.json in resources.");
      }
      
      repository.saveAll(objectMapper.readValue(inputStream, new TypeReference<List<Content>>(){}));
      
      repository.findAll().forEach(content -> System.out.println("Loaded content: " + content));
    } catch (IOException e) {
      System.err.println("Failed to load initial data: " + e.getMessage());
    }
  }
  
}
