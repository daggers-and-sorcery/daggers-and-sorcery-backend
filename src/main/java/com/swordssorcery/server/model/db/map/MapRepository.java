package com.swordssorcery.server.model.db.map;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MapRepository extends MongoRepository<MapDatabaseEntity, Integer> {
}