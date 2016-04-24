package com.morethanheroic.swords.combat.service.awarder;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.scavenging.domain.ScavengingResult;
import com.morethanheroic.swords.scavenging.domain.ScavengingResultEntity;
import com.morethanheroic.swords.scavenging.service.ScavengingFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScavengingAwarder {

    private static final String UNIDENTIFIED_ITEM_NAME = "Unidentified item";

    private final ScavengingFacade scavengingFacade;
    private final CombatMessageBuilder combatMessageBuilder;

    public void addScavengingDropsToUserFromMonsterDefinition(CombatResult combatResult, UserEntity userEntity, MonsterDefinition monster) {
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
    }
}
