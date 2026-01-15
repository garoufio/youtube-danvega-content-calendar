package com.youtube.danvega.content_calendar.repository;

import com.youtube.danvega.content_calendar.model.Content;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ContentRepository extends ListCrudRepository<Content, Integer> {

  List<Content> findByTitleContains(String keyword);
  
  //-------------------------------------------------------------------------------------------------------------------
  
  boolean existsByContent(Content content);
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
