package com.newsio.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.newsio.entities.NewsStory;
import com.newsio.models.NewsReponse;
import com.newsio.services.NewsService;

import io.jsonwebtoken.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/news")
public class NewsController {
  @Autowired
  private NewsService ns;

  @GetMapping("/")
  public String index() {
    ns = new NewsService();
    return "Welcome to the news controller g, you can't access this unless you're logged in.";
  }

  @GetMapping("/Search")
  public List<NewsReponse> search(@PathVariable String searchTextString) throws Exception{
    //ping the backend with the search text
    //then return it
    return ns.Search(searchTextString);
  }
}
