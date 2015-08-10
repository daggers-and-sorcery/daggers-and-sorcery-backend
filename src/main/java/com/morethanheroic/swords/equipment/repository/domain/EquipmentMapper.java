package com.morethanheroic.swords.equipment.repository.domain;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface EquipmentMapper {

    @Update("UPDATE equipment SET weapon = #{itemId} WHERE userId = #{userId}")
    void equipWeapon(@Param("userId") int userId, @Param("itemId") int itemId);

    @Update("UPDATE equipment SET offhand = #{itemId} WHERE userId = #{userId}")
    void equipOffhand(@Param("userId") int userId, @Param("itemId") int itemId);
}
