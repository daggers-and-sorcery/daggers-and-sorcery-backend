package com.morethanheroic.swords.equipment.repository.domain;

import com.morethanheroic.swords.equipment.repository.dao.EquipmentDatabaseEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface EquipmentMapper {

    @Select("SELECT * FROM equipment WHERE userId = #{userId}")
    EquipmentDatabaseEntity getEquipment(@Param("userId") int userId);

    @Update("UPDATE equipment SET weapon = #{itemId} WHERE userId = #{userId}")
    void equipWeapon(@Param("userId") int userId, @Param("itemId") int itemId);

    @Update("UPDATE equipment SET offhand = #{itemId} WHERE userId = #{userId}")
    void equipOffhand(@Param("userId") int userId, @Param("itemId") int itemId);
}
