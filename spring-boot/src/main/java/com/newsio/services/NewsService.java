package com.newsio.services;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
    NewsStory newsStory = new NewsStory();
    newsStory.setUrl(info.url);
    newsStory.setAuthorsByline(info.authorsByline);
    newsStory.setArticleId(info.articleId);
    newsStory.setClusterId(info.clusterId);
    newsStory.setSource(info.source);
    newsStory.setImageUrl(info.imageUrl);
    newsStory.setCountry(info.country);
    newsStory.setLanguage(info.language);
    newsStory.setPubDate(info.pubDate);
    newsStory.setScore(info.score);
    newsStory.setTitle(info.title);
    newsStory.setDescription(info.description);
    newsStory.setContent(info.content);
    newsStory.setMedium(info.medium);
    newsStory.setUser(user);
    newsStoryRepository.save(newsStory);
  }

  //funcition takes in a string to then ping the api with and then returns the results
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
        //NOTE need to go through the reponseresult here and serlize the String JSON to news story objects
        //then return the results here
    return new LinkedList<NewsStory>();
}

//creates a new mediastack api connection and returns it
public mediaStackAPI getMediaStackAPI(){
  return new mediaStackAPI();
}

}
