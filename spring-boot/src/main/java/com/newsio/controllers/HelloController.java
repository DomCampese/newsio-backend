package com.newsio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newsio.services.HelloService;

@RestController
public class HelloController {

  private HelloService helloService;

  // Autowired is used for dependency injection in springboot - helloService is automatically
  // supplied to this controller class - this lets us create mocks for testing very easily
  @Autowired
  public HelloController(HelloService helloService) {
    this.helloService = helloService;
  }
  
  @GetMapping("/")
  public String index() {
    return helloService.sayHello();
  }

  @PutMapping("/greetUs/{greeting}")
  public String sayItBack(@PathVariable String greeting) {
    return helloService.thankUserForGreeting(greeting);
  }
}
