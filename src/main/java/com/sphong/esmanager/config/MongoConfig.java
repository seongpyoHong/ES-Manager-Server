package com.sphong.esmanager.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;
import java.net.UnknownHostException;

@Configuration
//@EnableMongoRepositories
public class MongoConfig {
//    private static final String DB_NAME = "embedded";
//    private static final int DB_PORT = 27017;
//    private static final String DB_HOST = "127.0.0.1";
//    private static final String DB_COLLECTION = "products";
//
//    @Bean(name="mongoTemplate")
//    public MongoTemplate mongoTemplate(MongoClient mongoClient) throws IOException {
//        MongodStarter starter = MongodStarter.getDefaultInstance();
//        IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION).net(new Net(DB_HOST,DB_PORT, Network.localhostIsIPv6())).build();
//        MongodExecutable mongodExecutable = starter.prepare(mongodConfig);
//        MongodProcess mongodProcess = mongodExecutable.start();
//        MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
//
//        return new MongoTemplate(mongo, DB_NAME);
//    }
}

