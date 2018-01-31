package learning;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class Note {
  
  private UUID id;
  private String title;
  private Date date;
  private String comment;

  public Note(String title, String comment) {
    this.id = UUID.randomUUID();
    this.title = title;
    this.date = new Date();
    this.comment = comment;
  }

  public Note(String id, String title, Date date, String comment) {
    this(title, comment);
    this.id = UUID.fromString(id);
    this.date = date;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getDate() {
    return date;
  }
  
  public String getDateAsString() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String dateString = dateFormat.format(date);
    return dateString;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
  
  @Override
  public String toString() {
    return "Note [id=" + id + ", title=" + title + ", date=" + getDateAsString() + ", comment=" + comment + "]";
  }
}
