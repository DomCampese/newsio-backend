package com.newsio.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.newsio.entities.NewsStory;
import com.newsio.entities.User;
import com.newsio.entities.mediaStackAPI;
import com.newsio.models.NewsStoryInfo;
import com.newsio.repositories.NewsStoryRepository;
import com.newsio.repositories.UserRepository;

public class NewsService {
  @Autowired
  private NewsStoryRepository newsStoryRepository;
  @Autowired
  UserRepository userRepository;

  public NewsStory getNewsStory(String title) {
    return newsStoryRepository.findByTitle(title);
  }

  public void saveNewsStory(NewsStoryInfo info, User user) {
    NewsStory newsStory = NewsStory.builder()
      .url(info.url)
      .authorsByline(info.authorsByline)
      .articleId(info.articleId)
      .clusterId(info.clusterId)
      .source(info.source)
      .imageUrl(info.imageUrl)
      .country(info.country)
      .language(info.language)
      .pubDate(info.pubDate)
      .score(info.score)
      .title(info.title)
      .description(info.description)
      .content(info.content)
      .medium(info.medium)
      .user(user)
      .build();
    newsStoryRepository.save(newsStory);
  }

  //function takes in a string to then ping the api with and then returns the results
  public List<NewsStory> Search(String searchText) throws Exception{
    //catch empty search text here
    if(searchText.length() == 0){
      //debating returning a 400 error code here instead of a empty list
        return new LinkedList<NewsStory>();
    }
    //grab new mediastack connection
    mediaStackAPI ms = getMediaStackAPI();
    //get the string reponse
    String reponse = ms.sendGet(searchText);
    ArrayList<NewsStory> newsStorySearchResults = new ArrayList<NewsStory>();
    //create a google gson object to use for serialization and deserialization
    Gson gson = new Gson();
    //NOTE: I need to find a way to break up the many results that come in and deserialize each of them.
    //NOT DOEN YET BUT GETTING THERE
    NewsStory currentSearchResult = gson.fromJson(reponse, NewsStory.class);
    newsStorySearchResults.add(currentSearchResult);
    return newsStorySearchResults;
}

//creates a new mediastack api connection and returns it
public mediaStackAPI getMediaStackAPI(){
  return new mediaStackAPI();
}

}
