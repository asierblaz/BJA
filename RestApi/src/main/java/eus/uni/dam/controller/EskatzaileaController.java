package eus.uni.dam.controller;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;

import eus.uni.dam.model.Jokalaria;
import eus.uni.dam.model.Partida;
import eus.uni.dam.model.Umea;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController

public class EskatzaileaController {


	CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
    CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
    // Replace the uri string with your MongoDB deployment's connection string
    String uri = "mongodb://192.168.65.8:27017/?readPreference=primary&appname=MongoDB%20Compass&directConnection=true&ssl=false";
    String s="";
	   @GetMapping("/prueba")
	   public String get() {
		   try (MongoClient mongoClient = MongoClients.create(uri)) {
		        MongoDatabase database = mongoClient.getDatabase("mongobja").withCodecRegistry(pojoCodecRegistry);
		        MongoCollection<Partida> collection = database.getCollection("partidak", Partida.class);
		        
		        //read
		      /*  Umea umea = collection.find(eq("izena", "George")).first();
		        System.out.println(umea);
		      */
		        //insert  
		        Jokalaria j= new Jokalaria("111", "Jon", "S", 50);
				   
				   Partida p2 = new Partida(1, 500, null, j);
				   Date d = new Date();
				   Partida p=  new Partida(1, 100, d, j);

		        		      
		        InsertOneResult insert= collection.insertOne(p);
		        
		       return "Txertatua";
		   /*     Umea umea1 = collection.find(eq("izena", "asier")).first();
		        System.out.println(umea1);*/
		        
		        /*
		        
		        //delete            
		        DeleteResult delete = collection.deleteOne(eq("izena","asier"));
		      
		        //update 
		     collection.updateOne(eq("izena", "jon"),Updates.set("izena", "jon"));            */
		        
		      //read all
		 /*       ArrayList<Umea> umeak = new ArrayList<>();
		        collection.find().into(umeak);
		        umeak.forEach((i->s=s+i.toString()));
		        	
		       // s=umea.toString();*/

		    }
	   }
}
