package eus.uni.dam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import eus.uni.dam.controller.InsertMongoController;
import eus.uni.dam.controller.PartidaController;
import eus.uni.dam.dao.PartidaDAO;

@SpringBootApplication
public class ApiApplication {

	static PartidaController pc = new PartidaController();

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);

		pc.zebitzaria();
	//	 ic.greatServer();
	}

}
