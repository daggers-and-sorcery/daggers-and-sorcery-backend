package com.swordssorcery.server.model.repository;

import com.swordssorcery.server.model.News;

import java.util.List;

public interface CustomNewsRepository {

    List<News> findLast(int amount);
}
