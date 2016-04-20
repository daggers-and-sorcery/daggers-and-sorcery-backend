package com.morethanheroic.swords.combat.service.calc.result;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.adder.DropAwarder;
import com.morethanheroic.swords.combat.service.adder.ExperienceAwarder;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.scavenging.domain.ScavengingResult;
import com.morethanheroic.swords.scavenging.domain.ScavengingResultEntity;
import com.morethanheroic.swords.scavenging.service.ScavengingFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class PlayerVictoryHandler {

    private static final String UNIDENTIFIED_ITEM_NAME = "Unidentified item";

    private final DropAwarder dropAwarder;
    private final ExperienceAwarder experienceAwarder;
    private final ScavengingFacade scavengingFacade;
    private final CombatMessageBuilder combatMessageBuilder;
    private final UserMapper userMapper;

    public void handleVictory(Combat combat, CombatResult combatResult) {
        final UserEntity userEntity = combat.getUserCombatEntity().getUserEntity();
        final MonsterDefinition monster = combat.getMonsterCombatEntity().getMonsterDefinition();

        dropAwarder.addDropsToUserFromMonsterDefinition(combatResult, userEntity, monster);

        final ScavengingResult scavengingResult = scavengingFacade.handleScavenging(userEntity, monster);

        if (scavengingResult.isSuccessfulScavenge()) {
            for (ScavengingResultEntity scavengingResultEntity : scavengingResult.getScavengingResultList()) {
                if (scavengingResultEntity.isIdentified()) {
                    combatResult.addMessage(combatMessageBuilder.buildScavengeItemAwardMessage(scavengingResultEntity.getItem().getName(), scavengingResultEntity.getAmount()));
                } else {
                    combatResult.addMessage(combatMessageBuilder.buildScavengeItemAwardMessage(UNIDENTIFIED_ITEM_NAME, scavengingResultEntity.getAmount()));
                }
            }

            if (scavengingResult.getScavengingXp() > 0) {
                combatResult.addMessage(combatMessageBuilder.buildScavengeXpAwardMessage(scavengingResult.getScavengingXp()));
            }
        }

        experienceAwarder.addXpToUserFromMonsterDefinition(combatResult, userEntity);

        //TODO: Move user mapper outside, this class shouldn't know about user mapper at all, it should know more about user facade
        userMapper.updateBasicCombatStats(userEntity.getId(), combat.getUserCombatEntity().getActualHealth(), combat.getUserCombatEntity().getActualMana(), userEntity.getMovementPoints() - 1);
    }
}
