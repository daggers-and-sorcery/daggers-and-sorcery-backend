package com.morethanheroic.swords.equipment.repository.domain;

import com.morethanheroic.swords.equipment.repository.dao.EquipmentDatabaseEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface EquipmentMapper {

    @Select("SELECT * FROM equipment WHERE user_id = #{userId}")
    EquipmentDatabaseEntity getEquipment(@Param("userId") int userId);

    @Update("UPDATE equipment SET weapon = #{itemId} WHERE user_id = #{userId}")
    void equipWeapon(@Param("userId") int userId, @Param("itemId") int itemId);

    @Update("UPDATE equipment SET offhand = #{itemId} WHERE user_id = #{userId}")
    void equipOffhand(@Param("userId") int userId, @Param("itemId") int itemId);

    @Insert("INSERT INTO equipment SET user_id = #{userId}")
    void insert(@Param("userId") int userId);
}
