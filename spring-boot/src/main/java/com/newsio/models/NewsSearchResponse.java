package com.newsio.models;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsSearchResponse {
  public ArrayList<NewsStoryInfo> newsStories;
}
