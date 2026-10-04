package com.paloit.sdlc.sample;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint minimal servant de support aux scénarios du blueprint
 * (PR, relecture, tests requis, détection de secrets).
 */
@RestController
class HelloController {

	@GetMapping("/api/hello")
	Map<String, String> hello(@RequestParam(defaultValue = "monde") String name) {
		return Map.of("message", greeting(name));
	}

	static String greeting(String name) {
		String cleaned = name == null || name.isBlank() ? "monde" : name.trim();
		return "Bonjour " + cleaned;
	}

}
