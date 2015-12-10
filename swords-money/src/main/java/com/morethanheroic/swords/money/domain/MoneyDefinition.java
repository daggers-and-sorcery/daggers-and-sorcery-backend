package com.morethanheroic.swords.money.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Holds the static data of a {@link MoneyType} entity.
 */
@Getter
@Builder
@ToString
public class MoneyDefinition {

    private MoneyType id;
    private List<ConversionDefinition> conversionDefinitions;
}
