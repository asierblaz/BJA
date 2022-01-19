package eus.uni.dam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eus.uni.dam.dao.PartidaDAO;
import eus.uni.dam.model.Jokalaria;
import eus.uni.dam.model.Partida;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
@RestController
public class PartidaController {

	   @Autowired
	   private PartidaDAO partidaDao;
	
	   @GetMapping("/partidak")
	    public List<Partida> getPartidak() {
	        return partidaDao.findAll();
	    }
	   
	   @GetMapping("/partidak2")
	    public String getPartidak2() {
		   String s="hola";
		   pruebaJson();
	        return s;
	    }	 
	   
	   @GetMapping("/partida")
	    public ArrayList<Partida> getPartida() {
		  ArrayList<Partida> partidak= new ArrayList<>();
		   Jokalaria j= new Jokalaria("111", "Jon", "S", 50);
		   Partida p=  new Partida(1, 100, null, j);
		   
		   Partida p2 = new Partida(1, 500, null, j);
		   partidak.add(p);
		   partidak.add(p2);
		   return partidak;
	    }
	   
	   
	   	   
	   public void pruebaJson() {
		   
	        String data = "[{\"userName\": \"sandeep\",\"age\":30},{\"userName\": \"vivan\",\"age\":5}]  ";
	        
	        JSONArray jsonArr = new JSONArray(data);

	        for (int i = 0; i < jsonArr.length(); i++)
	        {
	            JSONObject jsonObj = jsonArr.getJSONObject(i);

	            System.out.println(jsonObj);
	        }
		   
	   }
	   
	   
}
