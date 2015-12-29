package com.morethanheroic.swords.attribute.service.calc.domain;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

/**
 * Holds all attribute data after a full attribute calculation. Creating this class is very resource intensive so try to avoid it as much
 * as possible.
 */
@Getter
@Builder
public class AttributeData {

    @NonNull
    private final Attribute attribute;

    @NonNull
    private final AttributeCalculationResult actual;

    @NonNull
    private final AttributeCalculationResult maximum;

    //TODO: remove array from it's name (especially because its a list).
    @NonNull
    private final List<AttributeModifierEntry> modifierDataArray;
}
