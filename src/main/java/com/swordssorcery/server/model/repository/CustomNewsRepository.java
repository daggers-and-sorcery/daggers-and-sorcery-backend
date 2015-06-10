package com.swordssorcery.server.model.repository;

import com.swordssorcery.server.model.News;

import java.util.List;

public interface CustomNewsRepository {

    public List<News> findLast(int amount);
}
