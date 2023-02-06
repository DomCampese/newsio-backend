package com.newsio.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.newsio.entities.NewsStory;
import com.newsio.entities.User;
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
}
