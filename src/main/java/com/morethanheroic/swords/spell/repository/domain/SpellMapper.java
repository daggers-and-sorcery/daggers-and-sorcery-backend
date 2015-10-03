package com.morethanheroic.swords.spell.repository.domain;

import com.morethanheroic.swords.spell.repository.dao.SpellDatabaseEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SpellMapper {

    @Select(value = "SELECT * FROM spells WHERE user_id = #{user_id}")
    List<SpellDatabaseEntity> getAllSpellsForUser(@Param("user_id") int userId);

    @Select(value = "SELECT count(*) FROM spells WHERE user_id = #{user_id} AND spell_id = #{spell_id}")
    int hasSpell(@Param("user_id") int userId, @Param("spell_id") int spellId);

    @Insert(value = "INSERT INTO spells SET user_id = #{user_id}, spell_id = #{spell_id}")
    void learnSpell(@Param("user_id") int userId, @Param("spell_id") int spellId);
}
