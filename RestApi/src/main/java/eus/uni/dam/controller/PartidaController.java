package eus.uni.dam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eus.uni.dam.dao.PartidaDAO;
import eus.uni.dam.model.Jokalaria;
import eus.uni.dam.model.Partida;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@RestController
public class PartidaController implements Runnable{
	
	   @Autowired
	    PartidaDAO partidaDao;
	   
	   ServerSocket zerbitzaria;
	   Socket s = new Socket();
	   int hariZenbatzailea;
	
	   @GetMapping("/partidak")
	    public List<Partida> getPartidak() {
	        return partidaDao.findAll();
	    }
	   @GetMapping("/partidakById")
	    public List<Partida> getPartidakById(@RequestParam(value="name")String name) {
	        return partidaDao.findByJokalari(name);
	    }
	   @GetMapping("/txarrenak")
	    public List<Partida> getPartidaTxarrenak() {
	        return partidaDao.findTxarrenak();
	    }
	   @GetMapping("/onenak")
	    public List<Partida> getPartidaOnenak() {
	        return partidaDao.findOnenak();
	    }
	   
	   @GetMapping("/init")
	   public String init() {
	   String s="<center><h1>Zerbitzaria Martxan</h1><br><img style='height:50%; width:50%' src='https://c.tenor.com/ofRPnrDi9SQAAAAC/loading.gif'></center><br><div id='loading'></div>"
	   + "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js\"></script><script>$.ajax({url: 'server',success: function (datos) {$('#loading').html('');}});</script>";
	   return s;
	   }
	   

	   @GetMapping("/server")
		public void greatServer() {

				hariZenbatzailea = 0;
				try {
					zerbitzaria = new ServerSocket(12345);
				
					System.out.println("Zerbitzaria martxan...");
					while(true)
					{
						s = zerbitzaria.accept(); // bezeroaren zai
						hariZenbatzailea++;
						Thread t = new Thread(this, "pc");
						t.start();
						// haria martxan jarri eta buklean jarraitu
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
	
		  }
	   
	   
	   @Override 
	   public void run() {
		   System.out.println("Zerbitzaria: HASI da.");
			try{  
				System.out.println("Zerbitzaria: 12345 portuan entzuten...");
				Socket socketBezeroarekin = s; // Konexioaren zai geratu
				System.out.println("Zerbitzaria: socketBezeroarekin onartuta eta sortuta, hari "+ hariZenbatzailea +" konektatuta dago. Mezuaren zai...");
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
				
				System.out.println("Zerbitzaria: fluxuak eta socket-ak isten...");
				
				dout.close();
				os.close();
				dis.close();
				is.close();
				s.close();
				
			}catch(Exception e)
			{
				System.out.println(e);
			}   
			System.out.println("Zerbitzaria: BUKATU da.");
	   }
	   
	  
	   
	   
}
