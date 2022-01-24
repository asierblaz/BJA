package eus.uni.dam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import eus.uni.dam.controller.InsertMongoController;

@SpringBootApplication
public class ApiApplication {
	
	static InsertMongoController ic = new InsertMongoController();

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);

		//	ic.zebitzaria();
		ic.greatServer();
	}

}
