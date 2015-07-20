package com.swordssorcery.server.model.db.repository;

import com.swordssorcery.server.model.db.News;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsRepository extends MongoRepository<News, String>, CustomNewsRepository {
}