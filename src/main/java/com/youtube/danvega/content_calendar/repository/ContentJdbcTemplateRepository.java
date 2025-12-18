package com.youtube.danvega.content_calendar.repository;

import com.youtube.danvega.content_calendar.model.Content;
import com.youtube.danvega.content_calendar.model.ContentStatus;
import com.youtube.danvega.content_calendar.model.ContentType;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        ContentType.valueOf(rs.getString("contentType")),
        rs.getTimestamp("date_created").toLocalDateTime(), // rs.getObject("date_created", LocalDateTime.class),
        rs.getTimestamp("date_updated") != null ? // rs.getObject("date_updated",LocalDateTime.class),
            rs.getTimestamp("date_updated").toLocalDateTime() : null,
        rs.getString("url")
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Content> getAllContent() {
    String sql = "SELECT * FROM content";
    List<Content> contentList = jdbcTemplate.query(sql, ContentJdbcTemplateRepository::mapRow);
    return contentList;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void createContent(String title, String description, ContentStatus status, ContentType contentType, String url) {
    String sql = "INSERT INTO content (title, description, status, contentType, date_created, url) " +
        "VALUES (?, ?, ?, ?, NOW(), ?)";
    jdbcTemplate.update(sql, title, description, status.name(), contentType.name(), url);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void updateContent(int id, String title, String description, ContentStatus status, ContentType contentType, String url) {
    String sql = "UPDATE content SET title = ?, description = ?, status = ?, content_type = ?, " +
        "date_updated = NOW(), url = ? WHERE id = ?";
    jdbcTemplate.update(sql, title, description, status.name(), contentType.name(), url, id);
  
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void deleteContent(int id) {
    String sql = "DELETE FROM content WHERE id = ?";
    jdbcTemplate.update(sql, id);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Content getContent(int id) {
    String sql = "SELECT * FROM content WHERE id = ?";
    Content content = jdbcTemplate.queryForObject(sql, new Object[]{id}, ContentJdbcTemplateRepository::mapRow);
    return content;
  }
  
  //-------------------------------------------------------------------------------------------------------------------

}
