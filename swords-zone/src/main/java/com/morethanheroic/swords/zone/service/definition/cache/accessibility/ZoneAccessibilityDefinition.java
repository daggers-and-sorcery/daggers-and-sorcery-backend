package com.morethanheroic.swords.zone.service.definition.cache.accessibility;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ZoneAccessibilityDefinition {

    private final ZoneAccessibilityRechargeRate rechargeRate;
    private final int count;
}
