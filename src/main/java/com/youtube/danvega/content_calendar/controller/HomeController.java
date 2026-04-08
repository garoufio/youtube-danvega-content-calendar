package com.youtube.danvega.content_calendar.controller;

import com.youtube.danvega.content_calendar.config.ContentCalendarProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HomeController {

//  @Value("${cc.welcomeMessage}")
//  private String welcomeMessage;
//  @Value("${cc.about}")
//  private String about;
  private final ContentCalendarProperties properties;
  
  public HomeController(ContentCalendarProperties properties) {
    this.properties = properties;
  }
  
//  @GetMapping("/")
//  public String home() {
//    return String.join("<br>", Map.of("welcomeMessage", welcomeMessage, "about", about).values());
//  }
  
  @GetMapping("/")
  public ContentCalendarProperties home() {
    return properties;
  }

}
