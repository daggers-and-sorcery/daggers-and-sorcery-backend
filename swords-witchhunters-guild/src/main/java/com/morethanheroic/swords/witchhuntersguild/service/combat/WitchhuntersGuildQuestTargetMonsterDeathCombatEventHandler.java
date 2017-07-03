package com.morethanheroic.swords.witchhuntersguild.service.combat;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.service.event.death.MonsterDeathCombatEventHandler;
import com.morethanheroic.swords.metadata.service.MetadataEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Check if the killed monster was a quest target.
 */
@Order(-1)
@Service
@RequiredArgsConstructor
public class WitchhuntersGuildQuestTargetMonsterDeathCombatEventHandler implements MonsterDeathCombatEventHandler {

    private final MetadataEntityFactory metadataEntityFactory;

    @Override
    public List<CombatStep> handleEvent(final CombatEntity deathEntity, final CombatEntity killerEntity) {
        final UserEntity userEntity = ((UserCombatEntity) killerEntity).getUserEntity();

        for (int i = 1; i <= 2; i++) {
            final int targetMonster = metadataEntityFactory.getNumericEntity(userEntity, "WITCHHUNTERS_GUILD_KILL_TARGET_" + i + "_MONSTER_ID").getValue();

            if (targetMonster == ((MonsterCombatEntity)deathEntity).getMonsterDefinition().getId()) {
                metadataEntityFactory.getNumericEntity(userEntity, "WITCHHUNTERS_GUILD_KILL_TARGET_" + i + "_MONSTER_COUNT").increaseValue(1);
            }
        }

        //TODO: Write some combat texts here!
        return Lists.newArrayList();
    }
}
