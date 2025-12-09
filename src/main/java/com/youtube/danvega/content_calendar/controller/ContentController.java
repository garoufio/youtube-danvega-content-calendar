package com.youtube.danvega.content_calendar.controller;

import com.youtube.danvega.content_calendar.model.Content;
import com.youtube.danvega.content_calendar.repository.ContentCollectionRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {
  
  private final ContentCollectionRepository contentRepository;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public ContentController(ContentCollectionRepository contentRepository) {
    this.contentRepository = contentRepository;
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  @GetMapping("/all")
  public List<Content> findAll() {
    return contentRepository.findAll();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @GetMapping("/{id}")
  public Content findById(@PathVariable Integer id) {
    return contentRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  public void create(@Valid @RequestBody Content c) {
    contentRepository.save(c);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping("/{id}")
  public void update(@RequestBody Content c, @PathVariable Integer id) {
    if (!contentRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
    }
    contentRepository.save(c);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Integer id) {
    contentRepository.deleteById(id);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}