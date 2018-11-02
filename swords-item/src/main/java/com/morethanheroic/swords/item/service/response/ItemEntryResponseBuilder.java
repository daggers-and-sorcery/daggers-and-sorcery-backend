package com.morethanheroic.swords.item.service.response;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.domain.modifier.ItemModifierDefinition;
import com.morethanheroic.swords.item.domain.requirement.ItemRequirementDefinition;
import com.morethanheroic.swords.item.service.response.domain.ItemRequirementResponseEntry;
import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Build an ItemEntryResponse.
 */
@Service
public class ItemEntryResponseBuilder {

    private static final int WEIGHT_DIVIDER = 100;
    private static final String POSITIVE_MODIFIER_PREFIX = " + ";

    public Map<String, Object> buildItemEntry(ItemDefinition itemDefinition) {
        final Map<String, Object> result = new HashMap<>();

        result.put("name", itemDefinition.getName());
        //TODO: later define real display name for item types somewhere (own definition?)
        result.put("type", WordUtils.capitalize(itemDefinition.getType().name().toLowerCase().replace("_", " ")));
        result.put("weight", (double) itemDefinition.getWeight() / WEIGHT_DIVIDER);
        result.put("equipment", itemDefinition.isEquipment());
        result.put("usable", itemDefinition.isUsable());
        if (itemDefinition.getFlavour() != null) {
            result.put("flavour", itemDefinition.getFlavour());
        }
        if (itemDefinition.getDescription() != null) {
            result.put("description", itemDefinition.getDescription());
        }

        final ArrayList<Map<String, Object>> modifiers = new ArrayList<>();
        result.put("modifiers", modifiers);

        for (ItemModifierDefinition modifierDefinition : itemDefinition.getModifiers()) {
            final Map<String, Object> modifier = new HashMap<>();

            //TODO: Load the names from definitions
            modifier.put("attribute", WordUtils.capitalize(modifierDefinition.getModifier().name().toLowerCase().replace("_", " ")));
            modifier.put("value", formatCombatAttributeModifier(modifierDefinition));

            modifiers.add(modifier);
        }

        final ArrayList<ItemRequirementResponseEntry> requirements = new ArrayList<>();
        result.put("requirements", requirements);

        for (ItemRequirementDefinition requirementDefinition : itemDefinition.getRequirements()) {
            requirements.add(new ItemRequirementResponseEntry(
                    WordUtils.capitalize(requirementDefinition.getRequirement().name().toLowerCase().replace("_", " ")),
                    requirementDefinition.getAmount())
            );
        }

        return result;
    }

    private String formatCombatAttributeModifier(ItemModifierDefinition attributeModifierValue) {
        String result = String.valueOf(attributeModifierValue.getAmount());

        if (attributeModifierValue.getD2() > 0) {
            result += POSITIVE_MODIFIER_PREFIX + attributeModifierValue.getD2() + "d2";
        }
        if (attributeModifierValue.getD4() > 0) {
            result += POSITIVE_MODIFIER_PREFIX + attributeModifierValue.getD4() + "d4";
        }
        if (attributeModifierValue.getD6() > 0) {
            result += POSITIVE_MODIFIER_PREFIX + attributeModifierValue.getD6() + "d6";
        }
        if (attributeModifierValue.getD8() > 0) {
            result += POSITIVE_MODIFIER_PREFIX + attributeModifierValue.getD8() + "d8";
        }
        if (attributeModifierValue.getD10() > 0) {
            result += POSITIVE_MODIFIER_PREFIX + attributeModifierValue.getD10() + "d10";
        }

        return result;
    }
}
