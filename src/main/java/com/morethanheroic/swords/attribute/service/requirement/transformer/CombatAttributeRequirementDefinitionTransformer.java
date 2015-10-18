package com.morethanheroic.swords.attribute.service.requirement.transformer;

import com.morethanheroic.swords.attribute.domain.requirement.CombatAttributeRequirementDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawCombatAttributeRequirementDefinition;
import org.springframework.stereotype.Service;

@Service
public class CombatAttributeRequirementDefinitionTransformer {

    public CombatAttributeRequirementDefinition transform(RawCombatAttributeRequirementDefinition rawBasicAttributeRequirementDefinition) {
        CombatAttributeRequirementDefinition.CombatAttributeRequirementDefinitionBuilder combatAttributeRequirementDefinitionBuilder = new CombatAttributeRequirementDefinition.CombatAttributeRequirementDefinitionBuilder();

        combatAttributeRequirementDefinitionBuilder.setAmount(rawBasicAttributeRequirementDefinition.getAmount());
        combatAttributeRequirementDefinitionBuilder.setAttribute(rawBasicAttributeRequirementDefinition.getAttribute());

        return combatAttributeRequirementDefinitionBuilder.build();
    }
}
