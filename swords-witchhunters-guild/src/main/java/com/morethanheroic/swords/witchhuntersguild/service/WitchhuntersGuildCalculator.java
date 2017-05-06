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

    private static final String WITCHUNTERS_GUILD_REPUTATION_POINTS = "WITCHUNTERS_GUILD_REPUTATION_POINTS";
    private static final String WITCHUNTERS_GUILD_UNLOCKED = "WITCHUNTERS_GUILD_UNLOCKED";
    private static final String WITCHUNTERS_GUILD_UNLOCKED_VALUE = "UNLOCKED";

    private final MetadataEntityFactory metadataEntityFactory;

    public boolean isWitchhuntersGuildUnlocked(final UserEntity userEntity) {
        return metadataEntityFactory.getTextEntity(userEntity, WITCHUNTERS_GUILD_UNLOCKED).getValue().equals(WITCHUNTERS_GUILD_UNLOCKED_VALUE);
    }

    public int calculateReputationPoints(final UserEntity userEntity) {
        return metadataEntityFactory.getNumericEntity(userEntity, WITCHUNTERS_GUILD_REPUTATION_POINTS).getValue();
    }
}
