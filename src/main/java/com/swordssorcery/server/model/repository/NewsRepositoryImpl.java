package com.swordssorcery.server.model.repository;

import com.swordssorcery.server.model.News;
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
    public List<News> findLast(int amount) {
        Query query = new Query();
        query.limit(10);
        query.with(new Sort(Sort.Direction.DESC, "timestamp"));

        return mongoOperations.find(query, News.class);
    }
}