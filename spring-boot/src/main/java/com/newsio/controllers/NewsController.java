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

  /* 
   * Let's just keep this as a simple passthrough - the frontend is responsible for making
   * things into comma separated lists (as it's actually harder to send arrays as) query
   * params.
   */
  @GetMapping("/search")
  public NewsSearchResponse search(
    @RequestParam String sources,
    @RequestParam String categories,
    @RequestParam String countries,
    @RequestParam String languages,
    @RequestParam String keywords,
    @RequestParam String date,
    @RequestParam String sort,
    @RequestParam String limit,
    @RequestParam String offset
  ) throws Exception {
    return newsService.search(sources, categories, countries, languages, keywords, date, sort, limit, offset);
  }
}
