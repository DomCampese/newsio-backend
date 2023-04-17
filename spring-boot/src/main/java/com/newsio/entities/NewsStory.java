package com.newsio.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="NewsStories")
public class NewsStory {
  @Id
  @GeneratedValue
  private Integer id;
  public String author;
  public String title;
  public String description;
  public String url;
  public String source;
  public String image;
  public String category;
  public String language;
  public String country;
  // provided by user that saves
  public String email;
  @Column(name = "published_at_column", columnDefinition = "TIMESTAMP")
  public LocalDateTime published_at;
  @ManyToOne(optional = true, fetch = FetchType.LAZY)
  User user;

}
