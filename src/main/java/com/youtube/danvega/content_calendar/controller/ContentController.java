package com.youtube.danvega.content_calendar.controller;

import com.youtube.danvega.content_calendar.model.Content;
import com.youtube.danvega.content_calendar.repository.ContentJdbcTemplateRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {
  
  //private final ContentCollectionRepository repository;
  //private final ContentRepository repository;
  private final ContentJdbcTemplateRepository repository;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public ContentController(ContentJdbcTemplateRepository repository) {
    this.repository = repository;
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  @GetMapping("/all")
  public List<Content> findAll() {
    //return (List<Content>) repository.findAll();
    return (List<Content>) repository.findAll();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @GetMapping("/{id}")
  public Content findById(@PathVariable Integer id) {
    Content content = repository.findById(id);
    if (content == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
    }
    return content;
//    return repository
//        .findById(id)
//        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  public void create(@Valid @RequestBody Content c) {
    int rowsAffected = repository.save(c.title(), c.description(), c.status(), c.contentType(), c.url());
    if (rowsAffected == 0) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create content");
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping("/{id}")
  public void update(@Valid @RequestBody Content c, @PathVariable Integer id) {
    //if (!repository.existsById(id)) {
    if (repository.findById(id) == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
    }
    repository.update(id, c.title(), c.description(), c.status(), c.contentType(), c.url());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Integer id) {
    int rowsAffected = repository.deleteById(id);
    if (rowsAffected == 0) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Failed to delete content with id = '" + id + "'");
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}