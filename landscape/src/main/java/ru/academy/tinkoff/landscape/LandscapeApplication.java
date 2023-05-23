package ru.academy.tinkoff.landscape;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class LandscapeApplication {
	public static void main(String[] args) {
		SpringApplication.run(LandscapeApplication.class, args);
	}
}
