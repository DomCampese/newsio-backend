package com.newsio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newsio.models.NewsSearchResponse;
import com.newsio.services.NewsService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/news")
public class NewsController {
  @Autowired
  private NewsService newsService;

  @GetMapping("/search")
  public NewsSearchResponse search(@RequestParam String searchTextString) throws Exception {
    //ping the backend with the search text
    //then return it
    return newsService.search(searchTextString);
  }
}
