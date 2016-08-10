package com.morethanheroic.swords.combat.repository.domain;

import com.morethanheroic.swords.combat.domain.AttackerType;
import com.morethanheroic.swords.combat.service.calc.attack.AttackType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CombatMapper {

    public void createCombat(@Param("userId") int userId, @Param("monsterId") int monsterId, @Param("monsterHealth") int monsterHealth, @Param("monsterMana") int monsterMana, @Param("nextAttacker") AttackerType nextAttacker);

    public void updateCombat(@Param("id") int id, @Param("monsterHealth") int monsterHealth, @Param("monsterMana") int monsterMana, @Param("nextAttacker") AttackerType nextAttacker);

    public void removeCombat(@Param("id") int id);
}
