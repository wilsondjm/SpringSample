package me.vincent.spring.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JavaPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaPracticeApplication.class, args);
	}
}
