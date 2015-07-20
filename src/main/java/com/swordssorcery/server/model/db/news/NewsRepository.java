package com.swordssorcery.server.model.db.news;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsRepository extends MongoRepository<NewsDatabaseEntity, String>, CustomNewsRepository {
}