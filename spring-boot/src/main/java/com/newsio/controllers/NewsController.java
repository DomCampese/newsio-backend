package com.newsio.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/news")
public class NewsController {
  @GetMapping("/")
  public String index() {
    return "Welcome to the news controller g, you can't access this unless you're logged in.";
  }
}
