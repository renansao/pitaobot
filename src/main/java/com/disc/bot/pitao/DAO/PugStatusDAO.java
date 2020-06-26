package com.disc.bot.pitao.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.disc.bot.pitao.domain.PugStatus;

@Repository
public interface PugStatusDAO extends MongoRepository<PugStatus, String>{

}
