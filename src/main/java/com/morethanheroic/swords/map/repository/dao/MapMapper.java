package com.morethanheroic.swords.map.repository.dao;

import com.morethanheroic.swords.map.repository.domain.MapDatabaseEntity;
import org.apache.ibatis.annotations.Select;

public interface MapMapper {

    @Select("SELECT * FROM map_entity WHERE id = #{id}")
    MapDatabaseEntity getById(int id);
}