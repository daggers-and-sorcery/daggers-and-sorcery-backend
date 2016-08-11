package com.morethanheroic.swords.combat.repository.domain;

import com.morethanheroic.swords.combat.domain.AttackerType;
import com.morethanheroic.swords.combat.repository.dao.CombatDatabaseEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface CombatMapper {

    @Insert("INSERT INTO combat SET user_id = #{userId}, monster_id = #{monsterId}, monster_health = #{monsterHealth}, monster_mana = #{monsterMana}, next_attacker = #{nextAttacker}")
    void createCombat(@Param("userId") int userId, @Param("monsterId") int monsterId, @Param("monsterHealth") int monsterHealth, @Param("monsterMana") int monsterMana, @Param("nextAttacker") AttackerType nextAttacker);

    @Update("UPDATE combat SET monster_health = #{monsterHealth}, monster_mana = #{monsterMana}, next_attacker = #{nextAttacker} WHERE id = #{id}")
    void updateCombat(@Param("id") int id, @Param("monsterHealth") int monsterHealth, @Param("monsterMana") int monsterMana, @Param("nextAttacker") AttackerType nextAttacker);

    @Delete("DELETE combat WHERE id = #{id}")
    void removeCombat(@Param("id") int id);

    @Select("SELECT * FROM combat WHERE user_id = #{userId}")
    CombatDatabaseEntity getRunningCombat(@Param("userId") int userId);
}
