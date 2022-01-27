package eus.uni.dam.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import eus.uni.dam.model.*;

import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

@Repository
public class PartidaDAO {
	
	@Autowired
    private MongoClient client;
	
    private MongoCollection<Partida> collection;

    @PostConstruct
    void datuakKargatu() {
        collection = client.getDatabase("mongobja").getCollection("partidak",Partida.class);
    }
    public List<Partida> findAll(){
    	return collection.find().into(new ArrayList<>());
    }
    public List<Partida> findByJokalari(String name){
    	Bson filter = Filters.all("jokalaria.name", name);
    	return collection.find(filter).into(new ArrayList<>());
    } 
    public List<Partida> findTxarrenak(){
    	List<Partida> bostTxarrenak=collection.find().sort(new BasicDBObject("puntuazioa",1)).limit(5).into(new ArrayList<>());    	
    	return bostTxarrenak;
    }
    public List<Partida> findOnenak(){
    	List<Partida> bostOnenak=collection.find().sort(new BasicDBObject("puntuazioa",-1)).limit(5).into(new ArrayList<>());    	
    	return bostOnenak;
    }   
    
   public void insertPartida(Partida p) {
	   collection.insertOne(p);
   }
} 
