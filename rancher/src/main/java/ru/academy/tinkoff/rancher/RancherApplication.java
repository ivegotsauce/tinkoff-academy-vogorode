package ru.academy.tinkoff.rancher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class RancherApplication {

	public static void main(String[] args) {
		SpringApplication.run(RancherApplication.class, args);
	}

}
