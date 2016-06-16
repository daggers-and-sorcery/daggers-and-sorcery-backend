package com.morethanheroic.swords.ladder.repository.domain;

import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LadderMapper {

    @Select("SELECT * FROM skills ORDER BY #{skill} LIMIT 20")
    List<SkillDatabaseEntity> getLadderData(@Param("skill") String skill);
}
