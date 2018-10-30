package com.morethanheroic.swords.combat.domain.effect.entry.domain.cause;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemCombatEffectCause implements CombatEffectCause {

    private final ItemDefinition itemDefinition;

    @Override
    public int getId() {
        return itemDefinition.getId();
    }

    @Override
    public String getName() {
        return itemDefinition.getName();
    }

    @Override
    public CombatEffectCauseType getType() {
        return CombatEffectCauseType.ITEM;
    }
}
