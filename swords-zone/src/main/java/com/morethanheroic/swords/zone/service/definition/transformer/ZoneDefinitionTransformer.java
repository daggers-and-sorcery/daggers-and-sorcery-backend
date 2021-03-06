package com.morethanheroic.swords.zone.service.definition.transformer;

import com.morethanheroic.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.zone.domain.ExplorationZone;
import com.morethanheroic.swords.zone.domain.ZoneDefinition;
import com.morethanheroic.swords.zone.service.definition.cache.accessibility.ZoneAccessibilityDefinition;
import com.morethanheroic.swords.zone.service.definition.loader.domain.RawZoneDefinition;
import org.springframework.stereotype.Service;

@Service
public class ZoneDefinitionTransformer implements DefinitionTransformer<ZoneDefinition, RawZoneDefinition> {

    @Override
    public ZoneDefinition transform(final RawZoneDefinition rawDefinition) {
        return ZoneDefinition.builder()
                .id(ExplorationZone.valueOf(rawDefinition.getId()))
                .name(rawDefinition.getName())
                .location(rawDefinition.getLocation())
                .description(rawDefinition.getDescription().trim())
                .enabled(rawDefinition.isEnabled())
                .minimumLevel(rawDefinition.getMinimumLevel())
                .maximumLevel(rawDefinition.getMaximumLevel())
                .accessibility(transformAccessibility(rawDefinition))
                .build();
    }

    private ZoneAccessibilityDefinition transformAccessibility(final RawZoneDefinition rawDefinition) {
        return rawDefinition.getAccessibility() == null ? null :
                ZoneAccessibilityDefinition.builder()
                        .rechargeRate(rawDefinition.getAccessibility().getRecharge())
                        .count(rawDefinition.getAccessibility().getCount())
                        .build();
    }
}
