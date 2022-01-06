package br.com.unisinos.pareapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
	private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	private final List<Greeting> greetings = new ArrayList<>();

	@GetMapping("/greeting")
	public ResponseEntity<Greeting> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name));
		greetings.add(greeting);
		log.info(getGreetings());
		ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok();
		return responseBuilder.body(greeting);
	}

	private String getGreetings() {
		return greetings.stream().map(Object::toString).reduce("", (a, b) -> a + b);
	}

	@PostMapping("/greeting")
	public ResponseEntity<Greeting> greeting(@RequestParam(value = "id") int id, @RequestParam(value = "message") String message) {
		Greeting greeting = new Greeting(id, message);
		greetings.add(greeting);
		log.info(getGreetings());
		ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok();
		return responseBuilder.body(greeting);
	}
}
