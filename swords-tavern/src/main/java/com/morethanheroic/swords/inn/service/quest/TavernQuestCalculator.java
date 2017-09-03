package com.morethanheroic.swords.inn.service.quest;

import com.morethanheroic.swords.metadata.service.MetadataEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TavernQuestCalculator {

    private static final String PLAYER_IS_VAMPIRE_METADATA_KEY = "PLAYER_IS_VAMPIRE";
    private static final String VAMPIRE_PLAYER_IS_VAMPIRE_METADATA_VALUE = "VAMPIRE";
    private static final String LOCATION_METADATA_KEY = "LOCATION";
    private static final String SEVGARD_LOCATION_METADATA_VALUE = "SEVGARD";

    private final MetadataEntityFactory metadataEntityFactory;

    public boolean shouldShowSmugglersHeavenQuest(final UserEntity userEntity) {
        return metadataEntityFactory.getTextEntity(userEntity, PLAYER_IS_VAMPIRE_METADATA_KEY).isValue(VAMPIRE_PLAYER_IS_VAMPIRE_METADATA_VALUE) &&
                metadataEntityFactory.getTextEntity(userEntity, LOCATION_METADATA_KEY).isValue(SEVGARD_LOCATION_METADATA_VALUE);
    }
}
