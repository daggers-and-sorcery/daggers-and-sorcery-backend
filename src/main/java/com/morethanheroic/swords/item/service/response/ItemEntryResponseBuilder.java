package com.morethanheroic.swords.item.service.response;

import com.morethanheroic.swords.attribute.domain.modifier.AttributeModifierDefinition;
import com.morethanheroic.swords.attribute.domain.modifier.CombatAttributeModifierDefinition;
import com.morethanheroic.swords.attribute.domain.requirement.AttributeRequirementDefinition;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.view.response.AttributeModifierResponseEntry;
import com.morethanheroic.swords.attribute.view.response.AttributeRequirementResponseEntry;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

@Service
public class ItemEntryResponseBuilder {

    public HashMap<String, Object> buildItemEntry(ItemDefinition itemDefinition) {
        HashMap<String, Object> result = new HashMap<>();

        result.put("name", itemDefinition.getName());
        result.put("type", itemDefinition.getType().name());
        result.put("weight", itemDefinition.getWeight());
        result.put("equipment", itemDefinition.isEquipment());
        result.put("usable", itemDefinition.isUsable());

        ArrayList<HashMap<String, Object>> modifiers = new ArrayList<>();
        result.put("modifiers", modifiers);

        for (AttributeModifierDefinition modifierDefinition : itemDefinition.getAllModifiers()) {
            HashMap<String, Object> modifier = new HashMap<>();

            modifier.put("attribute", modifierDefinition.getAttribute());
            if(modifierDefinition instanceof CombatAttributeModifierDefinition) {
                modifier.put("value", formatCombatAttributeModifier((CombatAttributeModifierDefinition) modifierDefinition));
            } else {
                modifier.put("value", modifierDefinition.getAmount());
            }

            modifiers.add(modifier);
        }

        ArrayList<AttributeRequirementResponseEntry> requirements = new ArrayList<>();
        result.put("requirements", requirements);

        for (AttributeRequirementDefinition requirementDefinition : itemDefinition.getAllRequirements()) {
            requirements.add(new AttributeRequirementResponseEntry(requirementDefinition.getAttribute(), requirementDefinition.getAmount()));
        }

        return result;
    }

    private String formatCombatAttributeModifier(CombatAttributeModifierDefinition attributeModifierValue) {
        String result = String.valueOf(attributeModifierValue.getAmount());

        if (attributeModifierValue.getD2() > 0) {
            result += " + " + attributeModifierValue.getD2() + "d2";
        }
        if (attributeModifierValue.getD4() > 0) {
            result += " + " + attributeModifierValue.getD4() + "d4";
        }
        if (attributeModifierValue.getD6() > 0) {
            result += " + " + attributeModifierValue.getD6() + "d6";
        }
        if (attributeModifierValue.getD8() > 0) {
            result += " + " + attributeModifierValue.getD8() + "d8";
        }
        if (attributeModifierValue.getD10() > 0) {
            result += " + " + attributeModifierValue.getD10() + "d10";
        }

        return result;
    }
}
