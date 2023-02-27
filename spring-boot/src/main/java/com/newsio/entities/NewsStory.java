package com.newsio.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
  private String url;
  private String authorsByline;
  private String articleId;
  private String clusterId;
  private String source;
  private String imageUrl;
  private String country;
  private String language;
  private Date pubDate;
  private double score;
  private String title;
  private String description;
  private String content;
  private String medium;
  @ManyToOne(optional = true, fetch = FetchType.LAZY)
  User user;
}
