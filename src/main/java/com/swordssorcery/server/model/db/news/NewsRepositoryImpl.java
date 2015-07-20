package com.swordssorcery.server.model.db.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class NewsRepositoryImpl implements CustomNewsRepository {

    private final MongoOperations mongoOperations;

    @Autowired
    public NewsRepositoryImpl(MongoOperations operations) {
        this.mongoOperations = operations;
    }

    @Override
    public List<NewsDatabaseEntity> findLast(int amount) {
        Query query = new Query();
        query.limit(10);
        query.with(new Sort(Sort.Direction.DESC, "date"));

        return mongoOperations.find(query, NewsDatabaseEntity.class);
    }
}