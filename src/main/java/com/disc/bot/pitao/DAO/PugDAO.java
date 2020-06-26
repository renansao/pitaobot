package com.disc.bot.pitao.DAO;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.disc.bot.pitao.domain.PugDomain;

@Configuration
@Repository
@EnableMongoRepositories
public interface PugDAO extends MongoRepository<PugDomain, String>{
	
}
