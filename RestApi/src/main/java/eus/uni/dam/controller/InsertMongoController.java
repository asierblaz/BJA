package eus.uni.dam.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import eus.uni.dam.dao.PartidaDAO;
import eus.uni.dam.model.Jokalaria;
import eus.uni.dam.model.Partida;

public class InsertMongoController {

	  @Autowired
	   private PartidaDAO partidaDao;
	
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
				while(str_mezua!="0") {
					
				
				
				
				
				
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
				}
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
	  
	  
	  public void greatServer() {
		  try {
			    ServerSocket zerbitzaria = new ServerSocket(12345);
			    Socket bezeroa = zerbitzaria.accept();
			           
			    InputStream is = bezeroa.getInputStream();
			    InputStreamReader isr = new InputStreamReader(is);
			    BufferedReader br = new BufferedReader(isr);

			    String jasotakoTestua;
			   
			    jasotakoTestua = br.readLine();


			    while ("agur" != jasotakoTestua) {
			   

			    if (jasotakoTestua != null) {
			    //JSONObject json = new JSONObject ();
			    //json.entrySet(jasotakoTestua);
	/*		    JSONParser jsonParser = new JSONParser();
			    Object json = jsonParser.parse(jasotakoTestua);
			    System.out.println(jasotakoTestua);
			               //System.out.println(json.toString());
			               
			               
			               try (FileWriter file = new FileWriter("employees.json")) {
			                   //We can write any JSONArray or JSONObject instance to the file
			                   file.write(json.toString());
			                   file.flush();
			       
			               } catch (IOException e) {
			                   e.printStackTrace();
			               }
			               
			               System.out.println(json.toString());*/
			    	
			    	System.out.println(jasotakoTestua);
			    	
			    }
			   
			    jasotakoTestua = br.readLine();
			   
			    }

			    bezeroa.close();
			    zerbitzaria.close();
			    } catch (IOException ex) {
			   
			    }
	  }
	   
	
}
