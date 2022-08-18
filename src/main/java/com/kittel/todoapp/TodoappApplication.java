package com.kittel.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoappApplication {

	public static void main(String[] args) {
		System.out.println("App gestartet.");
		SpringApplication.run(TodoappApplication.class, args);
	}

}
