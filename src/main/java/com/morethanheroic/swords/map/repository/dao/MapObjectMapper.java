package com.morethanheroic.swords.map.repository.dao;

import com.morethanheroic.swords.map.repository.domain.MapObjectDatabaseEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MapObjectMapper {

    @Select("SELECT * FROM map_object WHERE map_id = #{mapId}")
    List<MapObjectDatabaseEntity> getSpawnsForMap(int mapId);

    @Insert("INSERT INTO map_object SET x = #{x}, y = #{y}, map_id = #{mapId}, type = #{type}, object = #{object}")
    void saveSpawns(List<MapObjectDatabaseEntity> spawns);
}