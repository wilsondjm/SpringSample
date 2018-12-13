package me.vincent.spring.tutorial.configuration;


import me.vincent.spring.tutorial.repositoroy.mongodb.CustomedScoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;

@Configuration
@EnableMongoRepositories(basePackageClasses={CustomedScoresRepository.class})
public class MongoDBConfiguration {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public MongoRepositoryFactory getMongoRepositoryFactory(){
        MongoRepositoryFactory factory = new MongoRepositoryFactory(mongoTemplate);
        return factory;
    }
}
