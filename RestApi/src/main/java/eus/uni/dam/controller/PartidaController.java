package eus.uni.dam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eus.uni.dam.dao.PartidaDAO;
import eus.uni.dam.model.Jokalaria;
import eus.uni.dam.model.Partida;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
@RestController
public class PartidaController {

	   @Autowired
	 private  PartidaDAO partidaDao;
	
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
		   Jokalaria j= new Jokalaria("111", "Jon", 50);
		   Partida p=  new Partida(100, null, j);
		   
		   Partida p2 = new Partida(500, null, j);
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

	   
	   @GetMapping("/123")
	   public void zebitzaria() {
		   System.out.println("Zerbitzaria: HASI da.");
			try{  
				System.out.println("Zerbitzaria: 12345 portuan entzuten...");
				ServerSocket socketZerbitzaria = new ServerSocket(12345);  
				Socket socketBezeroarekin = socketZerbitzaria.accept(); // Konexioaren zai geratu
				System.out.println("Zerbitzaria: socketBezeroarekin onartuta eta sortuta. Mezuaren zai...");
				String  str_mezua ="";
				InputStream is = socketBezeroarekin.getInputStream(); 	// Bezeroarekin zabaldutako socket-etik irakurtzeko
				OutputStream os = socketBezeroarekin.getOutputStream(); // Bezeroari idazteko zabaldutako fluxua
				DataInputStream dis = new DataInputStream(is);  		// InputStream-arekin lan egiteko objektu bat
				DataOutputStream dout = new DataOutputStream(os);		// OutputStream-arekin lan egiteko objektu bat
					
				
				
				
				
				
				str_mezua = (String) dis.readUTF();  			// Mezu bat espero dugu eta irakurri egingo dugu
				JSONArray jsonArr = new JSONArray(str_mezua);

		        for (int i = 0; i < jsonArr.length(); i++)
		        {
		        	
		        	
		            JSONObject jsonObj = jsonArr.getJSONObject(i);
		            JSONObject partidaJSON= (JSONObject) jsonObj.get("partida");
		            JSONObject jokalariaJSON= (JSONObject) partidaJSON.get("jokalaria");

		            System.out.println(jsonObj);
		            
		            
		            String data= (String)partidaJSON.getString("fecha");
		            SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd");
		            Date fecha= formato.parse(data);
		            fecha= new Date();
		            int puntuazioa= partidaJSON.getInt("puntuacion");
		            String dni= (String)jokalariaJSON.getString("dni");
		            String name= (String)jokalariaJSON.getString("name");
		            int saldo= jokalariaJSON.getInt("saldo");
		            
		            Jokalaria j = new Jokalaria(dni,name,saldo);
		            Partida p= new Partida(puntuazioa,fecha,j);
		            
		            partidaDao.insertPartida(p);
		        }
				System.out.println("Jasotako mezua: " + str_mezua);
				
				
				
				String str_bidaltzeko = "Zerbitzariak jasotakoa: " + str_mezua + ". OK!!! Agur!";
				dout.writeUTF(str_bidaltzeko);  						// Bezeroari itzultzeko mezua
				dout.flush(); 
				
				// Gure protokoloaren arabera, ez dugu ezer gehiago espero, beraz, fluxuak eta socket-ak itxiko ditugu
				System.out.println("Zerbitzaria: fluxuak eta socket-ak isten...");
				dout.close();
				os.close();
				dis.close();
				is.close();
				socketBezeroarekin.close();
				socketZerbitzaria.close();
			}catch(Exception e)
			{
				System.out.println(e);
			}   
			System.out.println("Zerbitzaria: BUKATU da.");
	   }
	   
	   
}
