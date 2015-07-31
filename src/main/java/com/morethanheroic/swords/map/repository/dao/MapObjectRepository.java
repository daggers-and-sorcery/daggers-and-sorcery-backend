package com.morethanheroic.swords.map.repository.dao;

import com.morethanheroic.swords.map.repository.domain.MapObjectDatabaseEntity;
import org.springframework.data.repository.CrudRepository;

public interface MapObjectRepository extends CrudRepository<MapObjectDatabaseEntity, Integer> {
}