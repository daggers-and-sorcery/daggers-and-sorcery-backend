package com.morethanheroic.swords.spell.repository.domain;

import com.morethanheroic.swords.spell.repository.dao.SpellDatabaseEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SpellMapper {

    @Select("SELECT * FROM spells WHERE user_id = #{user_id}")
    List<SpellDatabaseEntity> getAllSpellsForUser(@Param("user_id") int userId);

    @Select("SELECT count(*) FROM spells WHERE user_id = #{user_id} AND spell_id = #{spell_id}")
    int hasSpell(@Param("user_id") int userId, @Param("spell_id") int spellId);

    @Insert("INSERT INTO spells SET user_id = #{user_id}, spell_id = #{spell_id}")
    void learnSpell(@Param("user_id") int userId, @Param("spell_id") int spellId);
}
