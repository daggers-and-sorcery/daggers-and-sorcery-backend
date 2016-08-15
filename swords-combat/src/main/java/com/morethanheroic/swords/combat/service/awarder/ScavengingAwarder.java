package com.morethanheroic.swords.combat.service.awarder;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.scavenging.domain.ScavengingResult;
import com.morethanheroic.swords.scavenging.domain.ScavengingResultEntity;
import com.morethanheroic.swords.scavenging.service.ScavengingFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScavengingAwarder {

    private static final String UNIDENTIFIED_ITEM_NAME = "Unidentified item";

    private final ScavengingFacade scavengingFacade;
    private final CombatMessageBuilder combatMessageBuilder;

    public List<CombatStep> addScavengingDropsToUserFromMonsterDefinition(UserEntity userEntity, MonsterDefinition monster) {
        final List<CombatStep> result = new ArrayList<>();

        final ScavengingResult scavengingResult = scavengingFacade.handleScavenging(userEntity, monster);

        if (scavengingResult.isSuccessfulScavenge()) {
            for (ScavengingResultEntity scavengingResultEntity : scavengingResult.getScavengingResultList()) {
                if (scavengingResultEntity.isIdentified()) {
                    result.add(
                            DefaultCombatStep.builder()
                                    .message(combatMessageBuilder.buildScavengeItemAwardMessage(scavengingResultEntity.getItem().getName(), scavengingResultEntity.getAmount()))
                                    .build()
                    );
                } else {
                    result.add(
                            DefaultCombatStep.builder()
                                    .message(combatMessageBuilder.buildScavengeItemAwardMessage(UNIDENTIFIED_ITEM_NAME, scavengingResultEntity.getAmount()))
                                    .build()
                    );
                }
            }

            if (scavengingResult.getScavengingXp() > 0) {
                result.add(
                        DefaultCombatStep.builder()
                                .message(combatMessageBuilder.buildScavengeXpAwardMessage(scavengingResult.getScavengingXp()))
                                .build()
                );
            }
        }

        return result;
    }

    @Deprecated
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
