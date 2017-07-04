package com.morethanheroic.swords.combat.service.calc.result;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.service.awarder.DropAwarder;
import com.morethanheroic.swords.combat.service.awarder.ExperienceAwarder;
import com.morethanheroic.swords.combat.service.awarder.ScavengingAwarder;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class PlayerVictoryHandler {

    private final DropAwarder dropAwarder;
    private final ExperienceAwarder experienceAwarder;
    private final ScavengingAwarder scavengingAwarder;

    public List<CombatStep> handleVictory(CombatContext combatContext) {
        final List<CombatStep> result = new ArrayList<>();

        final UserEntity userEntity = combatContext.getUser().getUserEntity();
        final MonsterDefinition monster = combatContext.getOpponent().getMonsterDefinition();

        result.addAll(dropAwarder.addDropsToUserFromMonsterDefinition(userEntity, monster));
        result.addAll(scavengingAwarder.addScavengingDropsToUserFromMonsterDefinition(userEntity, monster));
        result.addAll(experienceAwarder.addXpToUserFromMonsterDefinition(userEntity));

        final UserCombatEntity userCombatEntity = combatContext.getUser();
        userEntity.setBasicStats(userCombatEntity.getActualHealth(), userCombatEntity.getActualMana(), userEntity.getMovementPoints());

        return result;
    }
}
