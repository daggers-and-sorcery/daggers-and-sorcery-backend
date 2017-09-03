package com.morethanheroic.swords.location.service;

import com.morethanheroic.swords.location.domain.Location;
import com.morethanheroic.swords.metadata.service.MetadataEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationCalculator {

    private static final String LOCATION_METADATA_KEY = "LOCATION";

    private final MetadataEntityFactory metadataEntityFactory;

    public Location getLocation(final UserEntity userEntity) {
        return Location.valueOf(metadataEntityFactory.getTextEntity(userEntity, LOCATION_METADATA_KEY).getValue());
    }
}
