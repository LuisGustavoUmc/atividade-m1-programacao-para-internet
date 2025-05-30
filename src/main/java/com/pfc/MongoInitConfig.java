package com.pfc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoInitConfig implements CommandLineRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {
        // Criar a coleção de usuários, caso não exista
        if (!mongoTemplate.collectionExists("usuarios")) {
            mongoTemplate.createCollection("usuarios");
        }

        // Criar a coleção de vagas, caso não exista
        if (!mongoTemplate.collectionExists("vagas")) {
            mongoTemplate.createCollection("vagas");
        }
    }
}
