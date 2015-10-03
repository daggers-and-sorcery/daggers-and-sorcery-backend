package com.morethanheroic.swords.spell.repository.domain;

import com.morethanheroic.swords.spell.repository.dao.SpellDatabaseEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SpellMapper {

    @Select(value = "SELECT * FROM spells WHERE user_id = #{user_id}")
    List<SpellDatabaseEntity> getAllSpellsForUser(@Param("user_id") int userId);
}
