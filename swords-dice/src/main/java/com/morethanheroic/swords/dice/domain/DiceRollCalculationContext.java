package com.morethanheroic.swords.dice.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DiceRollCalculationContext {

    private int value;
    private int d2;
    private int d4;
    private int d6;
    private int d8;
    private int d10;
}
