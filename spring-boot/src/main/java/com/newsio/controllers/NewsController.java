package com.newsio.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.newsio.entities.NewsStory;
import com.newsio.models.NewsSearchResponse;
import com.newsio.models.NewsStoryInfo;
import com.newsio.services.NewsService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/news")
public class NewsController {
  @Autowired
  private NewsService newsService;

  /* 
   * Let's just keep this as a simple passthrough - the frontend is responsible for making
   * things into comma separated lists (as it's actually harder to send arrays as) query
   * params.
   */
  @GetMapping("/search")
  public NewsSearchResponse search(
    @RequestParam(required = false) String sources,
    @RequestParam(required = false) String categories,
    @RequestParam(required = false) String countries,
    @RequestParam(required = false) String languages,
    @RequestParam(required = false) String keywords,
    @RequestParam(required = false) String date,
    @RequestParam(required = false) String sort,
    @RequestParam(required = false) String limit,
    @RequestParam(required = false) String offset
  ) throws Exception {
    return newsService.search(sources, categories, countries, languages, keywords, date, sort, limit, offset);
  }

  // JSON object from front end deserializes automaticall into NewsStoryInfo object
  @PostMapping("/save")
  public void save(@RequestBody NewsStoryInfo info) {
    newsService.saveNewsStory(info);

  }

  // Simple method to return full list of saved news
  @GetMapping("/getSavedNews")
  public NewsSearchResponse getSavedNews() {
    System.out.println("GETTING SAVED NEWS");
    return newsService.getSavedNews();
  }
}
