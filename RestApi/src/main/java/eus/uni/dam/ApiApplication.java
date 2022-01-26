package eus.uni.dam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ApiApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
		logaIrekiProcess();
	}
	
	public static void logaIrekiProcess() {

		try {

		ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Mozilla Firefox\\firefox.exe",  "http://localhost:8080/init");
		pb.start();

		} catch (Exception e) {
		e.printStackTrace();
		}
		}

}

