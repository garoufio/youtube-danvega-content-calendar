package com.youtube.danvega.content_calendar.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(value = "Content")   // not necessary if class name matches table name
public record Content(
    @Id
    Integer id,
    @NotBlank
    String title,
    String description,
    ContentStatus status,
    @Column("content_type")
    ContentType contentType,
    @Column("date_created")
    LocalDateTime dateCreated,
    @Column("date_updated")
    LocalDateTime dateUpdated,
    String url
) { }
