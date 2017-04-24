package com.morethanheroic.swords.profile.response.service.attribute;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CombatAttributeCalculationResultPartialResponse extends AttributeCalculationResultPartialResponse {

    private final int d2;
    private final int d4;
    private final int d6;
    private final int d8;
    private final int d10;

    @Builder(builderMethodName = "buildCombatAttributeCalculationResultPartialResponse")
    private CombatAttributeCalculationResultPartialResponse(int value, int d2, int d4, int d6, int d8, int d10) {
        super(value);

        this.d2 = d2;
        this.d4 = d4;
        this.d6 = d6;
        this.d8 = d8;
        this.d10 = d10;
    }
}
