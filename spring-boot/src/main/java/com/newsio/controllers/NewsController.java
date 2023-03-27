package com.newsio.controllers;

import java.util.ArrayList;

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
  public NewsSearchResponse search(@RequestParam String searchTextString, @RequestParam ArrayList<String> locationFilter, @RequestParam ArrayList<String> sourceFilter, @RequestParam ArrayList<String> categorieFilter, @RequestParam ArrayList<String> languageFilter) throws Exception {
    /*OPTIONAL FILTERS: NOTE using the - symbol infront means exclude in the API
     * filtering by location,
       filtering by source (I.e. nbc, csb, -cnn,)
       filtering by predetermined categories like sports or technology.
       Categories the API supports:
        general - Uncategorized News
        business - Business News
        entertainment - Entertainment News
        health - Health News
        science - Science News
        sports - Sports News
        technology - Technology News
     */
    return newsService.search(searchTextString, locationFilter, sourceFilter, categorieFilter, languageFilter);
  }
}
