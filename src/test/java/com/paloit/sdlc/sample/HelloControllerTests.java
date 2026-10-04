package com.paloit.sdlc.sample;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HelloControllerTests {

	@Test
	void greetsGivenName() {
		assertThat(HelloController.greeting("Lamyaa")).isEqualTo("Bonsoir Lamyaa");
	}

	@Test
	void fallsBackWhenNameIsBlank() {
		assertThat(HelloController.greeting("  ")).isEqualTo("Bonjour monde");
	}

}
