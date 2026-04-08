package com.youtube.danvega.content_calendar.repository;

import com.youtube.danvega.content_calendar.model.Content;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentRepository extends ListCrudRepository<Content, Integer> {

  List<Content> findByTitleContains(String keyword);
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Query("""
    SELECT * FROM content WHERE status = :status
  """)
  List<Content> listByStatus(@Param("status") String status);
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Query("""
    SELECT * FROM content WHERE status = :status AND content_type = :contentType
  """)
  List<Content> listByStatusAndContentType(@Param("status") String status, @Param("contentType") String contentType);
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
