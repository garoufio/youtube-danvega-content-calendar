package com.youtube.danvega.content_calendar.repository;

import com.youtube.danvega.content_calendar.model.Content;
import com.youtube.danvega.content_calendar.model.ContentStatus;
import com.youtube.danvega.content_calendar.model.ContentType;
import org.springframework.data.relational.core.sql.UpdateBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ContentJdbcTemplateRepository {

  private final JdbcTemplate jdbcTemplate;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public ContentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /*
    This private static method maps a ResultSet row to a Content object.
    It extracts the data from the result set using appropriate getter methods and constructs a Content object.
    SQL exceptions may be thrown if issues occur while accessing the database.
 */
  private static Content mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new Content(
        rs.getInt("id"),
        rs.getString("title"),
        rs.getString("description"),
        ContentStatus.valueOf(rs.getString("status")),
        ContentType.valueOf(rs.getString("content_type")),
        rs.getTimestamp("date_created").toLocalDateTime(), // rs.getObject("date_created", LocalDateTime.class),
        rs.getTimestamp("date_updated") != null ? // rs.getObject("date_updated",LocalDateTime.class),
            rs.getTimestamp("date_updated").toLocalDateTime() : null,
        rs.getString("url")
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Content> getAll() {
    String sql = "SELECT * FROM content_calendar.content";
    List<Content> contentList = jdbcTemplate.query(sql, ContentJdbcTemplateRepository::mapRow);
    return contentList;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int create(String title, String description, ContentStatus status, ContentType contentType, String url) {
    String sql = "INSERT INTO content_calendar.content (title, description, status, content_type, date_created, url) " +
        "VALUES (?, ?, ?, ?, NOW(), ?)";
    return jdbcTemplate.update(sql, title, description, status.name(), contentType.name(), url);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int update(int id, String title, String description, ContentStatus status, ContentType contentType, String url) {
    String sql = "UPDATE content_calendar.content SET title = ?, description = ?, status = ?, content_type = ?, " +
        "date_updated = NOW(), url = ? WHERE id = ?";
    return jdbcTemplate.update(sql, title, description, status.name(), contentType.name(), url, id);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int deleteById(int id) {
    String sql = "DELETE FROM content_calendar.content WHERE id = ?";
    return jdbcTemplate.update(sql, id);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Content getById(int id) {
    String sql = "SELECT * FROM content_calendar.content WHERE id = ?";
    Content content = jdbcTemplate.query(sql, ContentJdbcTemplateRepository::mapRow, id)
        .stream()
        .findFirst()
        .orElse(null);
    return content;
  }
  
  //-------------------------------------------------------------------------------------------------------------------

}
