package com.morethanheroic.swords.skill.repository.domain;

import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SkillMapper {

    @Select("SELECT * FROM skills WHERE userId = #{userId}")
    SkillDatabaseEntity getSkills(@Param("userId") int userId);

    @Update("UPDATE skills SET twoHandedCrushingWeapons = twoHandedCrushingWeapons + value WHERE userId = #{userId}")
    void addTwoHandedCrushingWeaponsXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET oneHandedCrushingWeapons = oneHandedCrushingWeapons + value WHERE userId = #{userId}")
    void addOneHandedCrushingWeaponsXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET twoHandedAxesXp = twoHandedAxesXp + value WHERE userId = #{userId}")
    void addTwoHandedAxesXp(@Param("userId")int userId, @Param("value")long value);

    @Update("UPDATE skills SET oneHandedAxesXp = oneHandedAxesXp + value WHERE userId = #{userId}")
    void addOneHandedAxesXp(@Param("userId")int userId, @Param("value")long value);
}
