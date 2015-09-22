package com.morethanheroic.swords.map.repository.dao;

import com.morethanheroic.swords.map.repository.domain.MapObjectDatabaseEntity;
import com.morethanheroic.swords.map.repository.domain.MapObjectType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MapObjectMapper {

    @Select("SELECT * FROM map_object WHERE map_id = #{mapId}")
    List<MapObjectDatabaseEntity> getSpawnsForMap(int mapId);

    @Insert("INSERT INTO map_object SET x = #{x}, y = #{y}, map_id = #{mapId}, object_type = #{objectType}, object = #{object}")
    void saveSpawn(@Param("x") int x, @Param("y") int y, @Param("mapId") int mapId, @Param("objectType") MapObjectType type, @Param("object") int object);

    @Delete("DELETE FROM map_object WHERE id = #{id}")
    void removeSpawn(@Param("id") int id);
}