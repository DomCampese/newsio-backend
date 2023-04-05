package com.newsio.controllers;

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

  @GetMapping("/search")
  public NewsSearchResponse search(@RequestParam String searchTextString) throws Exception {
    //ping the backend with the search text
    //then return it
    return newsService.search(searchTextString);
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
