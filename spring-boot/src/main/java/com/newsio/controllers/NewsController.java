package com.newsio.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {

  @GetMapping("/News/")
  public String news() {
    return "Here are some news";
  }
}
