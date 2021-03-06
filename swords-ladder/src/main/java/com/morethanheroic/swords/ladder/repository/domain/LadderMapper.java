package com.morethanheroic.swords.ladder.repository.domain;

import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LadderMapper {

    @Select("SELECT * FROM skills ORDER BY ${skillname} DESC LIMIT #{limitStart}, #{limitEnd}")
    List<SkillDatabaseEntity> getLadderData(@Param("skillname") String skill, @Param("limitStart") int limitStart, @Param("limitEnd") int limitEnd);

    @Select("SELECT COUNT(*) FROM skills")
    int getSkillCount();
}
