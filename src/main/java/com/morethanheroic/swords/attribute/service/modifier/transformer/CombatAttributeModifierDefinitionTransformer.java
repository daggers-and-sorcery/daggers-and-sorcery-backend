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
        combatAttributeModifierDefinitionBuilder.setD2(rawCombatAttributeModifierDefinition.getD2());
        combatAttributeModifierDefinitionBuilder.setD4(rawCombatAttributeModifierDefinition.getD4());
        combatAttributeModifierDefinitionBuilder.setD6(rawCombatAttributeModifierDefinition.getD6());
        combatAttributeModifierDefinitionBuilder.setD8(rawCombatAttributeModifierDefinition.getD8());
        combatAttributeModifierDefinitionBuilder.setD10(rawCombatAttributeModifierDefinition.getD10());

        return combatAttributeModifierDefinitionBuilder.build();
    }
}
