package com.morethanheroic.swords.skill.repository.domain;

import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Contains mappings to sql queries to access skill data.
 */
@Repository
public interface SkillMapper {

    @Insert("INSERT INTO skills SET user_id = #{userId}")
    void insert(@Param("userId") int userId);

    @Select("SELECT * FROM skills WHERE user_id = #{userId}")
    SkillDatabaseEntity getSkills(@Param("userId") int userId);

    @Update("UPDATE skills SET two_handed_crushing_weapons_xp = two_handed_crushing_weapons_xp + #{value} WHERE user_id = #{userId}")
    void addTwoHandedCrushingWeaponsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET one_handed_crushing_weapons_xp = one_handed_crushing_weapons_xp + #{value} WHERE user_id = #{userId}")
    void addOneHandedCrushingWeaponsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET two_handed_axes_xp = two_handed_axes_xp + #{value} WHERE user_id = #{userId}")
    void addTwoHandedAxesXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET one_handed_axes_xp = one_handed_axes_xp + #{value} WHERE user_id = #{userId}")
    void addOneHandedAxesXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET throwing_weapons_xp = throwing_weapons_xp + #{value} WHERE user_id = #{userId}")
    void addThrowingWeaponsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET fistfight_xp = fistfight_xp + #{value} WHERE user_id = #{userId}")
    void addFistfightXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET longswords_xp = longswords_xp + #{value} WHERE user_id = #{userId}")
    void addLongswordsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET shortswords_xp = shortswords_xp + #{value} WHERE user_id = #{userId}")
    void addShortswordsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET polearms_xp = polearms_xp + #{value} WHERE user_id = #{userId}")
    void addPolearmsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET daggers_xp = daggers_xp + #{value} WHERE user_id = #{userId}")
    void addDaggersXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET longbows_xp = longbows_xp + #{value} WHERE user_id = #{userId}")
    void addLongbowsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET shortbows_xp = shortbows_xp + #{value} WHERE user_id = #{userId}")
    void addShortbowsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET crossbows_xp = crossbows_xp + #{value} WHERE user_id = #{userId}")
    void addCrossbowsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET light_armor_xp = light_armor_xp + #{value} WHERE user_id = #{userId}")
    void addLightArmorXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET heavy_armor_xp = heavy_armor_xp + #{value} WHERE user_id = #{userId}")
    void addHeavyArmorXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET robe_armor_xp = robe_armor_xp + #{value} WHERE user_id = #{userId}")
    void addRobeArmorXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET armorless_defense_xp = armorless_defense_xp + #{value} WHERE user_id = #{userId}")
    void addArmorlessDefenseXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET shield_defense_xp = shield_defense_xp + #{value} WHERE user_id = #{userId}")
    void addShieldDefenseXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET staffs_xp = staffs_xp + #{value} WHERE user_id = #{userId}")
    void addStaffsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET wands_xp = wands_xp + #{value} WHERE user_id = #{userId}")
    void addWandsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET spectres_xp = spectres_xp + #{value} WHERE user_id = #{userId}")
    void addSpectresXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET scavenging_xp = scavenging_xp + #{value} WHERE user_id = #{userId}")
    void addScavengingXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET cooking_xp = cooking_xp + #{value} WHERE user_id = #{userId}")
    void addCookingXp(@Param("userId") int userId, @Param("value") long value);
}
