package com.morethanheroic.swords.equipment.repository.domain;

import com.morethanheroic.swords.equipment.repository.dao.EquipmentDatabaseEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentMapper {

    @Select("SELECT * FROM equipment WHERE user_id = #{userId}")
    EquipmentDatabaseEntity getEquipment(@Param("userId") int userId);

    @Update("UPDATE equipment SET weapon = #{itemId}, weapon_identified = #{identified} WHERE user_id = #{userId}")
    void equipWeapon(@Param("userId") int userId, @Param("itemId") int itemId, @Param("identified") boolean identified);

    @Update("UPDATE equipment SET offhand = #{itemId}, offhand_identified = #{identified} WHERE user_id = #{userId}")
    void equipOffhand(@Param("userId") int userId, @Param("itemId") int itemId, @Param("identified") boolean identified);

    @Insert("INSERT INTO equipment SET user_id = #{userId}")
    void insert(@Param("userId") int userId);
}
