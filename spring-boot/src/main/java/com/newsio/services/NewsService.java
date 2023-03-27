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

  //function takes in a string to then ping the api with and then returns the results
  public NewsSearchResponse search(String searchText, ArrayList<String> locationFilter, ArrayList<String> sourceFilter, ArrayList<String> categorieFilter, ArrayList<String> languageFilter) throws Exception {
    //get the string reponse
    String reponse = mediaStackService.sendGet(searchText,locationFilter,sourceFilter,categorieFilter,languageFilter);
    MediaStackResponse serializedResponse = gson.fromJson(reponse, MediaStackResponse.class);
    return NewsSearchResponse.builder()
      .newsStories(serializedResponse.data)
      .build();
  }
}
