package com.newsio.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsStoryInfo {
  public String author;
  public String title;
  public String description;
  public String url;
  public String source;
  public String image;
  public String category;
  public String language;
  public String country;
  public String published_at;

}
