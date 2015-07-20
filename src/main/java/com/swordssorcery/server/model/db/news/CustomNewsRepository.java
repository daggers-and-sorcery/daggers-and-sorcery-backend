package com.swordssorcery.server.model.db.news;

import java.util.List;

public interface CustomNewsRepository {

    List<NewsDatabaseEntity> findLast(int amount);
}
