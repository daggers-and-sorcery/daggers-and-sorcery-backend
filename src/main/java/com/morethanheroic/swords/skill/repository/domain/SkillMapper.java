package com.morethanheroic.swords.skill.repository.domain;

import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SkillMapper {

    @Select("SELECT * FROM skills WHERE userId = #{userId}")
    SkillDatabaseEntity getSkills(@Param("userId") int userId);

    @Update("UPDATE skills SET twoHandedCrushingWeapons = twoHandedCrushingWeapons + #{value} WHERE userId = #{userId}")
    void addTwoHandedCrushingWeaponsXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET oneHandedCrushingWeapons = oneHandedCrushingWeapons + #{value} WHERE userId = #{userId}")
    void addOneHandedCrushingWeaponsXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET twoHandedAxesXp = twoHandedAxesXp + #{value} WHERE userId = #{userId}")
    void addTwoHandedAxesXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET oneHandedAxesXp = oneHandedAxesXp + #{value} WHERE userId = #{userId}")
    void addOneHandedAxesXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET throwingWeaponsXp = throwingWeaponsXp + #{value} WHERE userId = #{userId}")
    void addThrowingWeaponsXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET fistfightXp = fistfightXp + #{value} WHERE userId = #{userId}")
    void addFistfightXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET longswordsXp = longswordsXp + #{value} WHERE userId = #{userId}")
    void addLongswordsXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET shortswordsXp = shortswordsXp + #{value} WHERE userId = #{userId}")
    void addShortswordsXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET polearmsXp = polearmsXp + #{value} WHERE userId = #{userId}")
    void addPolearmsXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET daggersXp = daggersXp + #{value} WHERE userId = #{userId}")
    void addDaggersXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET longbowsXp = longbowsXp + #{value} WHERE userId = #{userId}")
    void addLongbowsXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET shortbowsXp = shortbowsXp + #{value} WHERE userId = #{userId}")
    void addShortbowsXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET crossbowsXp = crossbowsXp + #{value} WHERE userId = #{userId}")
    void addCrossbowsXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET lightArmorXp = lightArmorXp + #{value} WHERE userId = #{userId}")
    void addLightArmorXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET heavyArmorXp = heavyArmorXp + #{value} WHERE userId = #{userId}")
    void addHeavyArmorXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET robeArmorXp = robeArmorXp + #{value} WHERE userId = #{userId}")
    void addRobeArmorXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET armorlessDefenseXp = armorlessDefenseXp + #{value} WHERE userId = #{userId}")
    void addArmorlessDefenseXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET shieldDefenseXp = shieldDefenseXp + #{value} WHERE userId = #{userId}")
    void addShieldDefenseXp(@Param("userId")int userId, @Param("value")long value);
}
