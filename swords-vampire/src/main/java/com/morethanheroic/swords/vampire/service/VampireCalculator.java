package com.morethanheroic.swords.vampire.service;

import com.morethanheroic.swords.metadata.service.MetadataEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Used to calculate that the player is a vampire.
 */
@Service
@RequiredArgsConstructor
public class VampireCalculator {

    private static final String VAMPIRE_METADATA_ID = "PLAYER_IS_VAMPIRE";
    private static final String VAMPIRE_METADATA_VALUE_ID = "VAMPIRE";

    private final MetadataEntityFactory metadataEntityFactory;

    /**
     * Used to calculate that the player is a vampire.
     *
     * @param userEntity the player we are calculating for
     * @return true if the player is a vampire, false otherwise
     */
    public boolean isVampire(final UserEntity userEntity) {
        return metadataEntityFactory.getTextEntity(userEntity, VAMPIRE_METADATA_ID).getValue().equals(VAMPIRE_METADATA_VALUE_ID);
    }
}
