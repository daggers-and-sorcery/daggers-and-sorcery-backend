package com.morethanheroic.swords.item.service.response;

import com.morethanheroic.swords.item.service.response.domain.ItemRequirementResponseEntry;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.domain.ItemModifierDefinition;
import com.morethanheroic.swords.item.domain.ItemRequirementDefinition;
import org.apache.commons.lang.WordUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

//TODO: move this to web after item package is refactored to its own module
@Service
public class ItemEntryResponseBuilder {

    public HashMap<String, Object> buildItemEntry(ItemDefinition itemDefinition) {
        HashMap<String, Object> result = new HashMap<>();

        result.put("name", itemDefinition.getName());
        //TODO: later define real display name for item types somewhere (own definition?)
        result.put("type", WordUtils.capitalize(itemDefinition.getType().name().toLowerCase().replace("_", " ")));
        result.put("weight", itemDefinition.getWeight());
        result.put("equipment", itemDefinition.isEquipment());
        result.put("usable", itemDefinition.isUsable());

        ArrayList<HashMap<String, Object>> modifiers = new ArrayList<>();
        result.put("modifiers", modifiers);

        for (ItemModifierDefinition modifierDefinition : itemDefinition.getModifiers()) {
            HashMap<String, Object> modifier = new HashMap<>();

            modifier.put("attribute", modifierDefinition.getModifier());
            modifier.put("value", formatCombatAttributeModifier(modifierDefinition));

            modifiers.add(modifier);
        }

        ArrayList<ItemRequirementResponseEntry> requirements = new ArrayList<>();
        result.put("requirements", requirements);

        for (ItemRequirementDefinition requirementDefinition : itemDefinition.getRequirements()) {
            requirements.add(new ItemRequirementResponseEntry(requirementDefinition.getRequirement(), requirementDefinition.getAmount()));
        }

        return result;
    }

    private String formatCombatAttributeModifier(ItemModifierDefinition attributeModifierValue) {
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
