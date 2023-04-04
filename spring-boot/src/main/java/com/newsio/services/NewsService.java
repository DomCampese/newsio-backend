package com.newsio.services;

import java.util.ArrayList;

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

  // public void saveNewsStory(NewsStoryInfo info, User user) {
  //   NewsStory newsStory = NewsStory.builder()
  //     .url(info.url)
  //     .authorsByline(info.authorsByline)
  //     .articleId(info.articleId)
  //     .clusterId(info.clusterId)
  //     .source(info.source)
  //     .imageUrl(info.imageUrl)
  //     .country(info.country)
  //     .language(info.language)
  //     .pubDate(info.pubDate)
  //     .score(info.score)
  //     .title(info.title)
  //     .description(info.description)
  //     .content(info.content)
  //     .medium(info.medium)
  //     .user(user)
  //     .build();
  //   newsStoryRepository.save(newsStory);
  // }

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
