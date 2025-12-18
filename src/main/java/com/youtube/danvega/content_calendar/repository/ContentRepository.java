package com.youtube.danvega.content_calendar.repository;

import com.youtube.danvega.content_calendar.model.Content;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContentRepository extends CrudRepository<Content,Integer> {

  List<Content> findAllByContentType(String contentType);

}
