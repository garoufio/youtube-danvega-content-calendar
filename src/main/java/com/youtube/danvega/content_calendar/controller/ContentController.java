package com.youtube.danvega.content_calendar.controller;

import com.youtube.danvega.content_calendar.model.Content;
import com.youtube.danvega.content_calendar.repository.ContentJdbcTemplateRepository;
import com.youtube.danvega.content_calendar.repository.ContentRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {
  
  //private final ContentCollectionRepository repository;
//private final ContentJdbcTemplateRepository repository;
  private final ContentRepository repository;
  
  //-------------------------------------------------------------------------------------------------------------------
  
//  public ContentController(ContentJdbcTemplateRepository repository) {
  public ContentController(ContentRepository repository) {
    this.repository = repository;
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  @GetMapping("/all")
  public List<Content> findAll() {
    return repository.findAll();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @GetMapping("/{id}")
  public Content findById(@PathVariable Integer id) {
    Optional<Content> content = repository.findById(id);
    if (content.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
    } else return content.get();
//    return repository
//        .findById(id)
//        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @GetMapping("/filter/title/{keyword}")
  public List<Content> findByTitle(@PathVariable String keyword) {
    return repository.findByTitleContains(keyword);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @GetMapping("/filter/status/{status}")
  public List<Content> findByStatus(@PathVariable String status) {
    return repository.listByStatus(status);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @GetMapping("/filter/status/{status}/contentType/{contentType}")
  public List<Content> findByStatusAndContentType(@PathVariable String status, @PathVariable String contentType) {
    return repository.listByStatusAndContentType(status, contentType);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  public void create(@Valid @RequestBody Content c) {
    if (c.dateCreated() == null) {
      c = new Content(
          null,
          c.title(),
          c.description(),
          c.status(),
          c.contentType(),
          LocalDateTime.now(),
          null,
          c.url()
      );
    }
    repository.save(c);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping()
  public void update(@Valid @RequestBody Content c) {
    if (c.id() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Content ID is required for update");
    }
    Optional<Content> existingContentOpt = repository.findById(c.id());
    if (existingContentOpt.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
    }    
    Content newContent = new Content(
        null,
        c.title(),
        c.description(),
        c.status(),
        c.contentType(),
        c.dateCreated() == null ? existingContentOpt.get().dateCreated() : c.dateCreated(),
        LocalDateTime.now(),
        c.url()
    );
    repository.save(newContent);
    repository.deleteById(c.id());
    
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Integer id) {
    if (id == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Content ID is required for deletion");
    }
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
    }
    repository.deleteById(id);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}