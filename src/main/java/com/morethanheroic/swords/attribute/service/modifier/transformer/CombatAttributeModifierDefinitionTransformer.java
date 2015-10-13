package com.morethanheroic.swords.attribute.service.modifier.transformer;

import com.morethanheroic.swords.attribute.domain.modifier.CombatAttributeModifierDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawCombatAttributeModifierDefinition;
import org.springframework.stereotype.Service;

@Service
public class CombatAttributeModifierDefinitionTransformer {

    public CombatAttributeModifierDefinition transform(RawCombatAttributeModifierDefinition rawCombatAttributeModifierDefinition) {
        CombatAttributeModifierDefinition.CombatAttributeModifierDefinitionBuilder combatAttributeModifierDefinitionBuilder = new CombatAttributeModifierDefinition.CombatAttributeModifierDefinitionBuilder();

        combatAttributeModifierDefinitionBuilder.setAmount(rawCombatAttributeModifierDefinition.getAmount());
        combatAttributeModifierDefinitionBuilder.setAttribute(rawCombatAttributeModifierDefinition.getAttribute());

        return combatAttributeModifierDefinitionBuilder.build();
    }
}
