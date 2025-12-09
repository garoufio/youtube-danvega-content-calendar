package com.youtube.danvega.content_calendar.repository;

import com.youtube.danvega.content_calendar.model.Content;
import com.youtube.danvega.content_calendar.model.ContentStatus;
import com.youtube.danvega.content_calendar.model.ContentType;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {
  
  private final List<Content> contentList;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public ContentCollectionRepository() {
    contentList = new ArrayList<>();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @PostConstruct
  private void init() {
    Content c = new Content(
      1,
        "My first blog post",
        "This is the description of my first blog post",
        ContentStatus.IDEA,
        ContentType.ARTICLE,
        LocalDateTime.now(),
        null,
        ""
    );
    contentList.add(c);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Content> findAll() {
    return contentList;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Optional<Content> findById(Integer id) {
    return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void save(Content content) {
    contentList.removeIf(c -> c.id().equals(content.id()));
    contentList.add(content);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean existsById(Integer id) {
    return contentList.stream().anyMatch(c -> c.id().equals(id));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void deleteById(Integer id) {
    contentList.removeIf(c -> c.id().equals(id));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
