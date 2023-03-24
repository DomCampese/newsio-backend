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
import com.newsio.models.NewsReponse;
import com.newsio.models.NewsStoryInfo;
import com.newsio.models.PaginationInfo;
import com.newsio.repositories.NewsStoryRepository;
import com.newsio.repositories.UserRepository;

public class NewsService {
  @Autowired
  private NewsStoryRepository newsStoryRepository;
  @Autowired
  UserRepository userRepository;
  @Autowired
  private Gson gson;

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

  //function takes in a string to then ping the api with and then returns the results
  public List<NewsReponse> Search(String searchText) throws Exception{
    //catch empty search text here
    if(searchText.length() == 0){
      //debating returning a 400 error code here instead of a empty list
        return new LinkedList<NewsReponse>();
    }
    //grab new mediastack connection
    mediaStackAPI ms = getMediaStackAPI();
    //get the string reponse
    String reponse = ms.sendGet(searchText);//returns the body string from the reponse mediastack sends
    //PaginationInfo pi  = getPaginationInfo(reponse.substring(0,63));
    return getNewsReponse(reponse);
}

//creates a new mediastack api connection and returns it
private mediaStackAPI getMediaStackAPI(){
  return new mediaStackAPI();
}

//takes in a string containing pagination info and returns it as an obj
//NOTE if not needed can remove just thought it may be useful to collect this info
private PaginationInfo getPaginationInfo(String r){
  return gson.fromJson(r, PaginationInfo.class);
}

private List<NewsReponse> getNewsReponse(String data){
  ArrayList<NewsReponse> newsStories = new ArrayList<NewsReponse>();
  //get index of where data starts then grab an substring
  int dataIndex = data.indexOf("data");
  String newsData = data.substring(dataIndex);
  String[] newsStoriesArray = newsData.split("},");

  //now we go through the stories and clean them up then deseralize them into newreponse objects
  for(int i=0; i < newsStoriesArray.length; i++){
      String currentStory = newsStoriesArray[i];
      if(i == 0){
        //remove the "data:[" artifact in the first string
        currentStory = currentStory.substring(7);
        currentStory = currentStory.concat("}");
      }
      else if(i == (newsStoriesArray.length -1)){
        //remove the "]}} artifact thats present at the end string"
        currentStory = currentStory.substring(0, currentStory.length() - 3);
        currentStory = currentStory.concat("}");
      }
    NewsReponse deseralizedStory = gson.fromJson(currentStory, NewsReponse.class);
    newsStories.add(deseralizedStory);
  }
  return newsStories;
}
}
