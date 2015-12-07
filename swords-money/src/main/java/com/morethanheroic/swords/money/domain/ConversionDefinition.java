package com.morethanheroic.swords.money.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ConversionDefinition {

    private int targetId;
    private int conversionRate;
}
