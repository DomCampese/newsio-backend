package com.newsio.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Users")
public class User {
  @Id
  private Integer id;
  @Column(nullable=false, unique=true)
  private String username;
  @JsonIgnore
  @Column(nullable=false)
  private String password;
  @OneToMany(mappedBy="user")
  private List<NewsStory> newsStories = new ArrayList<>();

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<NewsStory> getNewsStories() {
    return newsStories;
  }
  
  public void setNewsStories(List<NewsStory> newsStories) {
    this.newsStories = newsStories;
  }
}
