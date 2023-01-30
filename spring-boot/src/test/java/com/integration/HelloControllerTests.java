package com.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.newsio.Application;
import com.newsio.controllers.HelloController;
import com.newsio.services.HelloService;

@SpringBootTest(classes=Application.class)
public class HelloControllerTests {
  // For integration we don't mock anything we want to make sure it works correctly
  private MockMvc mockMvc;
  @Autowired
  private HelloController helloController;

  @BeforeEach
  public void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
  }

  @Test
  public void testHelloEndpoint() throws Exception {
    mockMvc.perform(get("/")
           .accept(MediaType.parseMediaType("application/json")))
           .andExpect(status().isOk())
           .andExpect(content().contentType("application/json"))
           .andExpect(content().string("Hello world!"));
  }

  @Test
  public void testGreetUsEndpoint() throws Exception {
    String expectedResponse = "Thanks for saying yo";
    mockMvc.perform(put("/greetUs/yo")
           .accept(MediaType.parseMediaType("application/json")))
           .andExpect(status().isOk())
           .andExpect(content().contentType("application/json"))
           .andExpect(content().string(expectedResponse));
  }
}

