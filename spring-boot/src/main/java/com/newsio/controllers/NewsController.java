package com.newsio.controllers;

import java.util.ArrayList;
import java.text.ParseException;
import java.util.List;
import org.codehaus.jackson.node.ObjectNode;
import org.ietf.jgss.GSSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.newsio.entities.NewsStory;
import com.newsio.models.NewsSearchResponse;
import com.newsio.models.NewsStoryInfo;
import com.newsio.services.NewsService;
import io.jsonwebtoken.io.IOException;
import jakarta.validation.constraints.Null;
import nonapi.io.github.classgraph.json.JSONDeserializer;

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

  // JSON object from front end deserializes automaticall into NewsStoryInfo object
  @PostMapping("/save")
  public void save(@RequestBody NewsStoryInfo info) {
    newsService.saveNewsStory(info);

  }

  // Simple method to return full list of saved news
  @GetMapping("/getSavedNews")
  public List<NewsStory> getSavedNews() {
    return newsService.getSavedNews();
  }
}
