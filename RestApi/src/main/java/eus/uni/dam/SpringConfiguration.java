package eus.uni.dam;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
public class SpringConfiguration {

    
   // private String connectionString="mongodb://localhost:27017";//hobe application.properties fitxategian gordeko bagenu
    private String connectionString="mongodb://192.168.65.8:27017/?readPreference=primary&appname=MongoDB%20Compass&directConnection=true&ssl=false";//hobe application.properties fitxategian gordeko bagenu

    @Bean //Indicates that a method produces a bean to be managed by the Spring container. 
    public MongoClient mongoClient() {
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());//JavaObject <=> BSONdocument konbertsioa gauzatzeko 
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        return MongoClients.create(MongoClientSettings.builder()
                                                      .applyConnectionString(new ConnectionString(connectionString))
                                                      .codecRegistry(codecRegistry)
                                                      .build());
    }

}
