package com.swordssorcery.server.model.db.repository;

import com.swordssorcery.server.model.db.News;

import java.util.List;

public interface CustomNewsRepository {

    List<News> findLast(int amount);
}
