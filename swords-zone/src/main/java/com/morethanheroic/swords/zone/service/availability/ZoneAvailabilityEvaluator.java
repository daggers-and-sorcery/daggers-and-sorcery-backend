package com.morethanheroic.swords.zone.service.availability;

import com.morethanheroic.swords.location.domain.Location;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.zone.domain.ExplorationZone;
import com.morethanheroic.swords.zone.domain.ZoneDefinition;
import com.morethanheroic.swords.zone.service.definition.cache.ZoneDefinitionCache;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ZoneAvailabilityEvaluator {

    private final ZoneDefinitionCache zoneDefinitionCache;
    private final Map<ExplorationZone, ZoneAvailabilityModifier> zoneAvailabilityModifiers;

    public ZoneAvailabilityEvaluator(final ZoneDefinitionCache zoneDefinitionCache, final List<ZoneAvailabilityModifier> zoneAvailabilityModifierList) {
        this.zoneDefinitionCache = zoneDefinitionCache;

        zoneAvailabilityModifiers = zoneAvailabilityModifierList.stream()
                .collect(Collectors.toMap(ZoneAvailabilityModifier::isModifierFor, Function.identity()));
    }

    public List<ZoneDefinition> getAvailableZonesOnLocation(final UserEntity userEntity, final Location location) {
        return zoneDefinitionCache.getDefinitionsOnLocation(location).stream()
                .filter(zoneDefinition -> !zoneAvailabilityModifiers.containsKey(zoneDefinition.getId()) || zoneAvailabilityModifiers.get(zoneDefinition.getId()).isZoneAvailable(userEntity))
                .collect(Collectors.toList());
    }
}
