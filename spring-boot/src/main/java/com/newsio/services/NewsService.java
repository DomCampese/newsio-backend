package com.newsio.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.newsio.entities.NewsStory;
import com.newsio.models.MediaStackResponse;
import com.newsio.models.NewsSearchResponse;
import com.newsio.models.NewsStoryInfo;
import com.newsio.repositories.NewsStoryRepository;
import com.newsio.repositories.UserRepository;

@Service
public class NewsService {
  @Autowired
  private NewsStoryRepository newsStoryRepository;
  @Autowired
  UserRepository userRepository;
  @Autowired
  private Gson gson;
  @Autowired
  private MediaStackService mediaStackService;

  public NewsStory getNewsStory(String title) {
    return newsStoryRepository.findByTitle(title);
  }

  public void saveNewsStory(NewsStoryInfo info) { // (User user) -> removed unused parameter
    DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    LocalDateTime localDateTime = LocalDateTime.parse(info.published_at, formatter);
    
    NewsStory newsStory = NewsStory.builder()
      .author(info.author)
      .title(info.title)
      .description(info.description)
      .url(info.url)
      .source(info.source)
      .image(info.image)
      .category(info.category)
      .language(info.category)
      .country(info.country)
      .published_at(localDateTime)
      .build();
    newsStoryRepository.save(newsStory);

  }

  // returns all saved stories
  public List<NewsStory> getSavedNews() {
    return newsStoryRepository.findAll();
  }

  public NewsSearchResponse search(
    String sources,
    String categories,
    String countries,
    String languages,
    String keywords,
    String date,
    String sort,
    String limit,
    String offset
  ) throws Exception {
    
    String response = mediaStackService.sendGet(sources, categories, countries, languages, keywords, date, sort, limit, offset);
    MediaStackResponse serializedResponse = gson.fromJson(response, MediaStackResponse.class);
    return NewsSearchResponse.builder()
      .newsStories(serializedResponse.data)
      .build();
  }
}
