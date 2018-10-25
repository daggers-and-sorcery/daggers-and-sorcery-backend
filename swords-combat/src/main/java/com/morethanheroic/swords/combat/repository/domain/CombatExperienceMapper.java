package com.morethanheroic.swords.combat.repository.domain;

import com.morethanheroic.swords.combat.repository.dao.CombatExperienceDatabaseEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CombatExperienceMapper {

    //UNSAFE! If we ever going to change to master-slave based mysql setup we should fix this!
    @Insert("INSERT INTO combat_experience SET user_id = #{userId}, skill = #{skill}, amount = #{amount} ON DUPLICATE KEY UPDATE amount = amount + #{amount}")
    void addExperience(@Param("userId") int userId, @Param("skill") SkillType skill, @Param("amount") int amount);

    @Select("SELECT * FROM combat_experience WHERE user_id = #{userId}")
    List<CombatExperienceDatabaseEntity> getAll(@Param("userId") int userId);

    @Select("SELECT * FROM combat_experience WHERE user_id = #{userId} AND skill = #{skill}")
    CombatExperienceDatabaseEntity get(@Param("userId") int userId, @Param("skill") SkillType skill);

    @Delete("DELETE FROM combat_experience WHERE user_id = #{userId}")
    void removeAll(@Param("userId") int userId);
}
