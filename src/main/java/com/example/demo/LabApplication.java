package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class LabApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.example.demo");
		context.refresh();

		TeamRepository repository = context.getBean(TeamRepository.class);

		repository.add(new Team("Sevilla FC", "La Liga", 24));

		System.out.println(repository.getByKey("FC Barcelona").getLeague());

		context.close();

		// SpringApplication.run(LabApplication.class, args);
	}

}
