package com.swordssorcery.server.model.db.news;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NewsRepository extends CrudRepository<NewsDatabaseEntity, String> {

    @Query(value= "SELECT * FROM news ORDER BY date LIMIT 10", nativeQuery = true)
    List<NewsDatabaseEntity> findLastTen();
}