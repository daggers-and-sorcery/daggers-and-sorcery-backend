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

    @Update("UPDATE equipment SET gloves = #{itemId}, gloves_identified = #{identified} WHERE user_id = #{userId}")
    void equipGloves(@Param("userId") int userId, @Param("itemId") int itemId, @Param("identified") boolean identified);

    @Update("UPDATE equipment SET boots = #{itemId}, boots_identified = #{identified} WHERE user_id = #{userId}")
    void equipBoots(@Param("userId") int userId, @Param("itemId") int itemId, @Param("identified") boolean identified);

    @Update("UPDATE equipment SET amulet = #{itemId}, amulet_identified = #{identified} WHERE user_id = #{userId}")
    void equipAmulet(@Param("userId") int userId, @Param("itemId") int itemId, @Param("identified") boolean identified);

    @Update("UPDATE equipment SET chest = #{itemId}, chest_identified = #{identified} WHERE user_id = #{userId}")
    void equipChest(@Param("userId") int userId, @Param("itemId") int itemId, @Param("identified") boolean identified);

    @Update("UPDATE equipment SET legs = #{itemId}, legs_identified = #{identified} WHERE user_id = #{userId}")
    void equipLegs(@Param("userId") int userId, @Param("itemId") int itemId, @Param("identified") boolean identified);

    @Update("UPDATE equipment SET quiver = #{itemId}, quiver_identified = #{identified}, quiver_amount = #{quiverAmoint} WHERE user_id = #{userId}")
    void equipQuiver(@Param("userId") int userId, @Param("itemId") int itemId, @Param("identified") boolean identified, @Param("quiverAmoint") int quiverAmount);

    @Update("UPDATE equipment SET helm = #{itemId}, helm_identified = #{identified} WHERE user_id = #{userId}")
    void equipHelm(@Param("userId") int userId, @Param("itemId") int itemId, @Param("identified") boolean identified);

    @Update("UPDATE equipment SET bracer = #{itemId}, bracer_identified = #{identified} WHERE user_id = #{userId}")
    void equipBracer(@Param("userId") int userId, @Param("itemId") int itemId, @Param("identified") boolean identified);

    @Update("UPDATE equipment SET belt = #{itemId}, belt_identified = #{identified} WHERE user_id = #{userId}")
    void equipBelt(@Param("userId") int userId, @Param("itemId") int itemId, @Param("identified") boolean identified);

    @Update("UPDATE equipment SET ring = #{itemId}, ring_identified = #{identified} WHERE user_id = #{userId}")
    void equipRing(@Param("userId") int userId, @Param("itemId") int itemId, @Param("identified") boolean identified);

    @Insert("INSERT INTO equipment SET user_id = #{userId}")
    void insert(@Param("userId") int userId);
}
