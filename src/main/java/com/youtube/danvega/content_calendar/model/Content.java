package com.youtube.danvega.content_calendar.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record Content(
    @Id
    Integer id,
    @NotBlank
    String title,
    String description,
    ContentStatus status,
    ContentType contentType,
    LocalDateTime dateCreated,
    LocalDateTime dateUpdated,
    String url
) { }
