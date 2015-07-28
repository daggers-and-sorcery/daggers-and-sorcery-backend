package com.morethanheroic.swords.map.repository.dao;

import com.morethanheroic.swords.map.repository.domain.MapDatabaseEntity;
import org.springframework.data.repository.CrudRepository;

public interface MapRepository extends CrudRepository<MapDatabaseEntity, Integer> {
}