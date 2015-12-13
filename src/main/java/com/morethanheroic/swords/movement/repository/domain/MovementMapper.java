package com.morethanheroic.swords.movement.repository.domain;

import com.morethanheroic.swords.movement.repository.dao.MovementDatabaseEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementMapper {

    @Select("SELECT * FROM position WHERE user_id = #{userId}")
    MovementDatabaseEntity findEntity(@Param("userId") int userId);

    @Update("UPDATE position SET x = #{x}, y = #{y} WHERE user_id = #{userId}")
    void updatePosition(@Param("userId") int userId, @Param("x") int x, @Param("y") int y);

    @Insert("INSERT INTO position SET x = #{x}, y = #{y}, map_id = #{mapId}, user_id = #{userId}")
    void createNewPosition(@Param("userId") int userId, @Param("mapId") int mapId, @Param("x") int x, @Param("y") int y);
}
