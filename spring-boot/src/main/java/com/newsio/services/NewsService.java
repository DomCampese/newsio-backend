package com.newsio.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

  public void saveNewsStory(NewsStoryInfo info, String email) { // (User user) -> removed unused parameter
    DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    LocalDateTime localDateTime = LocalDateTime.parse(info.published_at, formatter);
    
    NewsStory newsStory = NewsStory.builder()
      .author(info.author)
      .title(info.title)
      .description(info.description.length() < 252 ? info.description : info.description.substring(0, 252) + "...")
      .url(info.url)
      .source(info.source)
      .image(info.image)
      .category(info.category)
      .language(info.category)
      .country(info.country)
      .published_at(localDateTime)
      .email(email)
      .build();
    newsStoryRepository.save(newsStory);

  }

  public void unsaveNewsStory(NewsStoryInfo info, String email) {
    List<NewsStory> list = newsStoryRepository.findAll();

    for(NewsStory story : list) {
      if(story.published_at.toString().equals(info.published_at) && story.title.equals(info.title) && email.equals(story.email)){
        newsStoryRepository.delete(story);
      }
    }
    
  }

  // returns all saved stories
  public NewsSearchResponse getSavedNews(String email) {
    List<NewsStory> stories = newsStoryRepository.findAll();
    // converts to ArrayList of NewsStoryInfo to be formatted into NewsSearchResponse
    ArrayList<NewsStoryInfo> infoList = new ArrayList<>();

    for(NewsStory currNews : stories) {
      // make sure it is the right user that saved
      if (email.equals(currNews.email)){
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
      
    }

    return NewsSearchResponse.builder().newsStories(infoList).build();
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
