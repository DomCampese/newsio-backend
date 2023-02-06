package com.example.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.newsio.Application;
import com.newsio.example.controllers.HelloController;
import com.newsio.example.services.HelloService;

@SpringBootTest(classes=Application.class)
public class HelloControllerTests {
  private MockMvc mockMvc;
  // Mocking dependencies is really easy in springboot you just use to @Mock annotation
  // then @InjectMocks for the object you're unit testing
  @Mock
  private HelloService mockHelloService;
  @InjectMocks
  private HelloController helloController;

  @BeforeEach
  public void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
  }

  @Test
  public void testHelloEndpoint() throws Exception {
    Mockito.when(mockHelloService.sayHello()).thenReturn("A string");
    mockMvc.perform(get("/")
           .accept(MediaType.parseMediaType("application/json")))
           .andExpect(status().isOk())
           .andExpect(content().contentType("application/json"))
           .andExpect(content().string("A string"));
  }

  @Test
  public void testGreetUsEndpoint() throws Exception {
    String expectedResponse = "Thanks for saying hi";
    Mockito.when(mockHelloService.thankUserForGreeting("hi")).thenReturn(expectedResponse);
    mockMvc.perform(put("/greetUs/hi")
           .accept(MediaType.parseMediaType("application/json")))
           .andExpect(status().isOk())
           .andExpect(content().contentType("application/json"))
           .andExpect(content().string(expectedResponse));
  }
}
