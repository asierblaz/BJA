package eus.uni.dam;

import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import eus.uni.dam.dao.PartidaDAO;
import eus.uni.dam.model.Jokalaria;
import eus.uni.dam.model.Partida;

@RestController
public class HariZerbitzaria extends Thread {
	
	private PartidaDAO partidaDao;
	
	DataInputStream dis;
	DataOutputStream dout;
	Socket socket = null;
	
	String agurMezua = "agur";
	int hariZenbakia = -1;
	
	public HariZerbitzaria()
	{
		
	}
	
	public HariZerbitzaria(int hariZenbakia, Socket s, PartidaDAO p)
	{
		this.hariZenbakia = hariZenbakia;
		this.socket = s;
		this.partidaDao = p;
		start();

	}
	
	public void run()
	{
		String  str_mezua ="";
		while(str_mezua!="0") {		
			try {
				Thread.sleep(5000);
				InputStream is = socket.getInputStream(); 	// Bezeroarekin zabaldutako socket-etik irakurtzeko
				OutputStream os = socket.getOutputStream(); // Bezeroari idazteko zabaldutako fluxua
				dis = new DataInputStream(is);  		// InputStream-arekin lan egiteko objektu bat
				dout = new DataOutputStream(os);		// OutputStream-arekin lan egiteko objektu bat

				if((String) dis.readUTF() != null) {
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
			} catch (IOException | ParseException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		System.out.println("HariZerbitzaria (" + this.hariZenbakia + ") bezeroarekin komunikazioa bukatu da: " + this.socket.toString());
		try {
			dis.close();
			dout.close();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/*@GetMapping("/1234")
	public void greatServer() {
		  ServerSocket zerbitzaria;
			Socket bezeroa;
			HariZerbitzaria hariZerbitzaria;
			int hariZenbatzailea = 0;
			try {
				zerbitzaria = new ServerSocket(12345);
			
				System.out.println("Zerbitzaria martxan...");
				while(true)
				{
					bezeroa = new Socket();
					bezeroa = zerbitzaria.accept(); // bezeroaren zai
					hariZenbatzailea++;
					hariZerbitzaria = new HariZerbitzaria(hariZenbatzailea, bezeroa);
					hariZerbitzaria.start(); // haria martxan jarri eta buklean jarraitu
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//	ic.zebitzaria();
		
	  }*/

}
