package com.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.newsio.services.HelloService;

@SpringBootTest(classes=HelloService.class)
class HelloServiceTests {

	@Autowired
	private HelloService helloService;

	@Test
	void sayHelloReturnsHello() {
		String result = helloService.sayHello();
		assertEquals("Hello world!", result);
	}

	@Test
	void thankUserForGreetingThanksUserForAppropriateGreeting() {
		String greeting = "ayo";
		String result = helloService.thankUserForGreeting(greeting);
		assertEquals("Thanks for saying " + greeting, result);
	}
}
