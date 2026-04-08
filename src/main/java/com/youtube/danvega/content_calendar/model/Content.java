package com.youtube.danvega.content_calendar.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(value = "content", schema = "content_calendar")   // not necessary if class name matches table name
public record Content(
    @Id
    Integer id,
    @NotBlank
    String title,
    String description,
    @NotNull
    ContentStatus status,
    @Column("content_type")
    @NotNull
    ContentType contentType,
    @Column("date_created")
    LocalDateTime dateCreated,
    @Column("date_updated")
    LocalDateTime dateUpdated,
    String url
) { }
