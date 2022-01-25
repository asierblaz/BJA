package eus.uni.dam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import eus.uni.dam.controller.InsertMongoController;

@SpringBootApplication
public class ApiApplication {
	
	static HariZerbitzaria ic = new HariZerbitzaria();

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);

		// ic.zebitzaria();
		// ic.greatServer();
		logaIrekiProcess();
	}
	
	public static void logaIrekiProcess() {

		try {

		ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Mozilla Firefox\\firefox.exe",  "http://localhost:8080/1234");
		pb.start();

		} catch (Exception e) {
		e.printStackTrace();
		}
		}

}

