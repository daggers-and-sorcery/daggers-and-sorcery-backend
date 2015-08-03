package com.morethanheroic.swords.map.repository.dao;

import com.morethanheroic.swords.map.repository.domain.MapDatabaseEntity;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MapMapper {

    @Update("UPDATE map_entity SET id = #{id} WHERE id = #{id}")
    void update(MapDatabaseEntity mapDatabaseEntity);

    @Select("SELECT * FROM map_entity WHERE id = #{id}")
    MapDatabaseEntity getById(int id);
}