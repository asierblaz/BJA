package eus.uni.dam.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import eus.uni.dam.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

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
   public void insertPartida(Partida p) {
	   collection.insertOne(p);
   }
} 
