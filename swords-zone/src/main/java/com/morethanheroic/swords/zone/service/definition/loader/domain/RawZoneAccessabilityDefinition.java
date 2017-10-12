package com.morethanheroic.swords.zone.service.definition.loader.domain;

import com.morethanheroic.swords.zone.service.definition.cache.accessibility.ZoneAccessibilityRechargeRate;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "accessibility")
public class RawZoneAccessabilityDefinition {

    private int count;
    private ZoneAccessibilityRechargeRate recharge;
}
