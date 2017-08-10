package com.morethanheroic.swords.combat.service.calc.terminate.victory;

import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.service.calc.terminate.victory.domain.PlayerVictoryContext;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.service.awarder.DropAwarder;
import com.morethanheroic.swords.combat.service.awarder.ExperienceAwarder;
import com.morethanheroic.swords.combat.service.awarder.ScavengingAwarder;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerVictoryHandler {

    private final DropAwarder dropAwarder;
    private final ExperienceAwarder experienceAwarder;
    private final ScavengingAwarder scavengingAwarder;

    public List<CombatStep> handleVictory(final PlayerVictoryContext playerVictoryContext) {
        updateUserStatistics(playerVictoryContext);

        return awardRewardsForMonster(playerVictoryContext);
    }

    private void updateUserStatistics(final PlayerVictoryContext playerVictoryContext) {
        final UserEntity userEntity = playerVictoryContext.getUser().getUserEntity();
        final UserCombatEntity userCombatEntity = playerVictoryContext.getUser();

        userEntity.setBasicStats(userCombatEntity.getActualHealth(), userCombatEntity.getActualMana(), userEntity.getMovementPoints());
    }

    private List<CombatStep> awardRewardsForMonster(final PlayerVictoryContext playerVictoryContext) {
        final UserEntity userEntity = playerVictoryContext.getUser().getUserEntity();
        final MonsterDefinition monsterDefinition = playerVictoryContext.getOpponent().getMonsterDefinition();

        final List<CombatStep> result = new ArrayList<>();

        result.addAll(dropAwarder.addDropsToUserFromMonsterDefinition(userEntity, monsterDefinition));
        result.addAll(scavengingAwarder.addScavengingDropsToUserFromMonsterDefinition(userEntity, monsterDefinition));
        result.addAll(experienceAwarder.addXpToUserFromMonsterDefinition(userEntity));

        return result;
    }
}
