package com.youtube.danvega.content_calendar.controller;

import com.youtube.danvega.content_calendar.model.Content;
import com.youtube.danvega.content_calendar.repository.ContentCollectionRepository;
import com.youtube.danvega.content_calendar.repository.ContentRepository;
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
  private final ContentRepository repository;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public ContentController(ContentRepository contentRepository) {
    this.repository = contentRepository;
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  @GetMapping("/all")
  public List<Content> findAll() {
    //return contentRepository.findAll();
    return (List<Content>) repository.findAll();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @GetMapping("/{id}")
  public Content findById(@PathVariable Integer id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  public void create(@Valid @RequestBody Content c) {
    repository.save(c);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping("/{id}")
  public void update(@RequestBody Content c, @PathVariable Integer id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
    }
    repository.save(c);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Integer id) {
    repository.deleteById(id);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}