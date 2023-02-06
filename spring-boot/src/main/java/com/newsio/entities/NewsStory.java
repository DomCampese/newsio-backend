package com.newsio.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="NewsStories")
public class NewsStory {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String author;
  private String title;
  private String description;
  private String url;
  private String source;
  private String image;
  private String category;
  private String language;
  private String country;
  @Temporal(TemporalType.TIMESTAMP)
  Date publishedAt;

  public NewsStory(String author, String title, String description, String url,
                   String source, String image, String category, String language,
                   String country, Date publishedAt) {
    this.author = author;
    this.title = title;
    this.description = description;
    this.url = url;
    this.source = source;
    this.image = image;
    this.category = category;
    this.language = language;
    this.country = country;
    this.publishedAt = publishedAt;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Date getPublishedAt() {
    return publishedAt;
  }

  public void setPublishedAt(Date publishedAt) {
    this.publishedAt = publishedAt;
  }
}
