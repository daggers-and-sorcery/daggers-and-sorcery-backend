package com.swordssorcery.server.model.repository;

import com.swordssorcery.server.model.News;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsRepository extends MongoRepository<News, String>, CustomNewsRepository {
}