package com.youtube.danvega.content_calendar.model;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record Content(
    Integer id,
    @NotBlank
    String title,
    String description,
    ContentStatus status,
    ContentType contentType,
    LocalDateTime dateCrated,
    LocalDateTime dateUpdated,
    String url
) {
}
