package com.newsio.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  
  @GetMapping("/")
  public String index() {
    return "Hello world from SpringBoot";
  }
}
