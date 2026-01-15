package com.youtube.danvega.content_calendar.controller;

import com.youtube.danvega.content_calendar.model.Content;
import com.youtube.danvega.content_calendar.repository.ContentJdbcTemplateRepository;
import com.youtube.danvega.content_calendar.repository.ContentRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {
  
  //private final ContentCollectionRepository repository;
//  private final ContentJdbcTemplateRepository repository;
  private final ContentRepository repository;
  
  //-------------------------------------------------------------------------------------------------------------------
  
//  public ContentController(ContentJdbcTemplateRepository repository) {
  public ContentController(ContentRepository repository) {
    this.repository = repository;
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  @GetMapping("/all")
  public List<Content> findAll() {
    return (List<Content>) repository.findAll();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @GetMapping("/{id}")
  public Content findById(@PathVariable Integer id) {
    Optional<Content> content = repository.findById(id);
    if (content.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
    }
    return content.get();
//    return repository
//        .findById(id)
//        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @GetMapping("/filter/title/{keyword}")
  public List<Content> findByTitleContains(@PathVariable String keyword) {
    return repository.findByTitleContains(keyword);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  public void create(@Valid @RequestBody Content c) {
    
    Content content = repository.save(c);
    if (content == null) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create content");
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping("/{id}")
  public void update(@Valid @RequestBody Content c, @PathVariable Integer id) {
    if (!repository.existsById(id)) {
    //if (repository.findById(id) == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
    }
    repository.deleteById(id);
    repository.save(c);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Integer id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
    }
    repository.deleteById(id);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}