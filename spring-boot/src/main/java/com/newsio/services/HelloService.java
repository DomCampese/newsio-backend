package com.newsio.services;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
  public String sayHello() {
    return "Hello world!";
  }

  public String thankUserForGreeting(String greeting) {
    return "Thanks for saying " + greeting;
  }
}
