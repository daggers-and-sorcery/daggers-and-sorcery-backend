package com.morethanheroic.swords.money.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Holds the data of a money conversion, how different types of the same money type can be converted between different items (for
 * example the data of conversion from bronze to silver/gold/platinum etc...).
 */
@Getter
@Builder
@ToString
public class Conversion {

    private int targetId;
    private int conversionRate;
}
