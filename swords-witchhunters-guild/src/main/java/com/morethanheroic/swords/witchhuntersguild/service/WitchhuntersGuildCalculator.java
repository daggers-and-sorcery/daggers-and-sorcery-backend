package com.morethanheroic.swords.witchhuntersguild.service;

import com.morethanheroic.swords.metadata.service.MetadataEntityFactory;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Calculate the Witchhunter's Guild reputation points of a user.
 */
@Service
@RequiredArgsConstructor
public class WitchhuntersGuildCalculator {

    private static final String WITCHHUNTERS_GUILD_UNLOCKED = "WITCHHUNTERS_GUILD_UNLOCKED";
    private static final String WITCHHUNTERS_GUILD_UNLOCKED_VALUE = "UNLOCKED";

    private static final String WITCHHUNTERS_GUILD_REPUTATION_POINTS = "WITCHHUNTERS_GUILD_REPUTATION_POINTS";

    private final MetadataEntityFactory metadataEntityFactory;

    public boolean isWitchhuntersGuildUnlocked(final UserEntity userEntity) {
        return metadataEntityFactory.getTextEntity(userEntity, WITCHHUNTERS_GUILD_UNLOCKED).getValue().equals(WITCHHUNTERS_GUILD_UNLOCKED_VALUE);
    }

    //TODO: More rep point calculation out into it's own calculator class
    public int calculateReputationPoints(final UserEntity userEntity) {
        return metadataEntityFactory.getNumericEntity(userEntity, WITCHHUNTERS_GUILD_REPUTATION_POINTS).getValue();
    }

    public void increaseReputationPoints(final UserEntity userEntity, final int amount) {
        metadataEntityFactory.getNumericEntity(userEntity, WITCHHUNTERS_GUILD_REPUTATION_POINTS).setValue(calculateReputationPoints(userEntity) + amount);
    }

    public int calculateKilledCount(final UserEntity userEntity, final MonsterDefinition monsterDefinition) {
        for (int i = 1; i <= 2; i++) {
            if (metadataEntityFactory.getNumericEntity(userEntity, "WITCHHUNTERS_GUILD_KILL_TARGET_" + i + "_MONSTER_ID").getValue() == monsterDefinition.getId()) {
                return metadataEntityFactory.getNumericEntity(userEntity, "WITCHHUNTERS_GUILD_KILL_TARGET_" + i + "_MONSTER_COUNT").getValue();
            }
        }

        throw new IllegalStateException("The player tries to access killcount for a witchhunters quest monster that doesn't exists.");
    }
}
