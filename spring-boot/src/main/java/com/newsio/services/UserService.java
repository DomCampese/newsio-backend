package com.newsio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newsio.entities.NewsStory;
import com.newsio.entities.User;
import com.newsio.repositories.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired PasswordEncoder passwordEncoder;

  public void saveUser(String username, String plaintextPassword) {
    User user = new User();
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(plaintextPassword));
    userRepository.save(user);
  }

  public User getUser(String username) {
    return userRepository.findByUsername(username);
  }

  public void addNewsStory(User user, NewsStory newsStory) {
    List<NewsStory> newsStories = user.getNewsStories();
    newsStories.add(newsStory);
    userRepository.save(user);
  }
}
