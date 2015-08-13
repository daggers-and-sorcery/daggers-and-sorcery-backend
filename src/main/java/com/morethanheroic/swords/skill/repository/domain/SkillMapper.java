package com.morethanheroic.swords.skill.repository.domain;

import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SkillMapper {

    @Select("")
    SkillDatabaseEntity getSkills(@Param("userId") int userId);
}
