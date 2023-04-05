package com.newsio.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.newsio.entities.NewsStory;
import com.newsio.entities.User;
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
  public NewsSearchResponse getSavedNews() {
    System.out.println("GET SAVED NEWS CALLED");
    List<NewsStory> stories = newsStoryRepository.findAll();
    // converts to ArrayList of NewsStoryInfo to be formatted into NewsSearchResponse
    ArrayList<NewsStoryInfo> infoList = new ArrayList<>();

    for(NewsStory currNews : stories) {
      System.out.println(currNews.title);
      infoList.add(NewsStoryInfo.builder()
                  .author(currNews.author)
                  .title(currNews.title)
                  .description(currNews.description)
                  .url(currNews.url)
                  .source(currNews.source)
                  .image(currNews.image)
                  .category(currNews.category)
                  .language(currNews.category)
                  .country(currNews.country)
                  .published_at(currNews.published_at.toString())
                  .build());
      
    }

    return NewsSearchResponse.builder().newsStories(infoList).build();
  }

  //function takes in a string to then ping the api with and then returns the results
  public NewsSearchResponse search(String searchText) throws Exception {
    //get the string reponse
    String reponse = mediaStackService.sendGet(searchText);//returns the body string from the reponse mediastack sends
    //PaginationInfo pi  = getPaginationInfo(reponse.substring(0,63));
    MediaStackResponse serializedResponse = gson.fromJson(reponse, MediaStackResponse.class);
    return NewsSearchResponse.builder()
      .newsStories(serializedResponse.data)
      .build();
  }
}
