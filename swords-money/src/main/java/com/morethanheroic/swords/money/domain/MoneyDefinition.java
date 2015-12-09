package com.morethanheroic.swords.money.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Holds the static data of a {@link Money} entity.
 */
@Getter
@Builder
@ToString
public class MoneyDefinition {

    private Money id;
    private List<ConversionDefinition> conversionDefinitions;
}
