package com.morethanheroic.swords.witchhuntersguild.service;

import com.morethanheroic.swords.metadata.service.MetadataEntityFactory;
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

    public int calculateReputationPoints(final UserEntity userEntity) {
        return metadataEntityFactory.getNumericEntity(userEntity, WITCHHUNTERS_GUILD_REPUTATION_POINTS).getValue();
    }
}
