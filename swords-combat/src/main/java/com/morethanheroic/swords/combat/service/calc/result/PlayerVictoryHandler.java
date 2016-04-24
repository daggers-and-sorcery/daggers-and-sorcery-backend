package com.morethanheroic.swords.combat.service.calc.result;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.awarder.DropAwarder;
import com.morethanheroic.swords.combat.service.awarder.ExperienceAwarder;
import com.morethanheroic.swords.combat.service.awarder.ScavengingAwarder;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class PlayerVictoryHandler {

    private final DropAwarder dropAwarder;
    private final ExperienceAwarder experienceAwarder;
    private final ScavengingAwarder scavengingAwarder;

    public void handleVictory(Combat combat, CombatResult combatResult) {
        final UserEntity userEntity = combat.getUserCombatEntity().getUserEntity();
        final MonsterDefinition monster = combat.getMonsterCombatEntity().getMonsterDefinition();

        dropAwarder.addDropsToUserFromMonsterDefinition(combatResult, userEntity, monster);
        scavengingAwarder.addScavengingDropsToUserFromMonsterDefinition(combatResult, userEntity, monster);
        experienceAwarder.addXpToUserFromMonsterDefinition(combatResult, userEntity);

        final UserCombatEntity userCombatEntity = combat.getUserCombatEntity();
        userEntity.setBasicStats(userCombatEntity.getActualHealth(), userCombatEntity.getActualMana(), userEntity.getMovementPoints() - 1);
    }
}
