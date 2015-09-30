package com.morethanheroic.swords.item.service;

import com.morethanheroic.swords.attribute.view.response.AttributeModifierResponseEntry;
import com.morethanheroic.swords.attribute.view.response.AttributeRequirementResponseEntry;
import com.morethanheroic.swords.item.service.domain.AttributeModifierDefinition;
import com.morethanheroic.swords.item.service.domain.AttributeRequirementDefinition;
import com.morethanheroic.swords.item.service.domain.ItemDefinition;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class ItemEntryResponseBuilder {

    public HashMap<String, Object> buildItemEntry(ItemDefinition itemDefinition) {
        HashMap<String, Object> result = new HashMap<>();

        result.put("name", itemDefinition.getName());
        result.put("type", itemDefinition.getType().name());
        result.put("weight", itemDefinition.getWeight());
        result.put("equipment", itemDefinition.isEquipment());
        result.put("usable", itemDefinition.isUsable());

        ArrayList<AttributeModifierResponseEntry> modifiers = new ArrayList<>();
        result.put("modifiers", modifiers);

        for (AttributeModifierDefinition modifierDefinition : itemDefinition.getAllModifiers()) {
            modifiers.add(new AttributeModifierResponseEntry(modifierDefinition.getAttribute(), modifierDefinition.getAmount()));
        }

        ArrayList<AttributeRequirementResponseEntry> requirements = new ArrayList<>();
        result.put("requirements", requirements);

        for (AttributeRequirementDefinition requirementDefinition : itemDefinition.getAllRequirements()) {
            requirements.add(new AttributeRequirementResponseEntry(requirementDefinition.getAttribute(), requirementDefinition.getAmount()));
        }

        return result;
    }
}
