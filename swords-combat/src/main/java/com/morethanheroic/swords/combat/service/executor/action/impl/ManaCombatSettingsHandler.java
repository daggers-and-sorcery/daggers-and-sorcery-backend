package com.morethanheroic.swords.combat.service.executor.action.impl;

import com.morethanheroic.math.PercentageCalculator;
import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;
import com.morethanheroic.swords.combat.service.executor.action.CombatSettingsActionHandler;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.combat.service.UseItemService;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import com.morethanheroic.swords.spell.service.UseSpellService;

public class ManaCombatSettingsHandler extends CombatSettingsActionHandler {

    private final PercentageCalculator percentageCalculator;

    public ManaCombatSettingsHandler(UseItemService useItemService, ItemDefinitionCache itemDefinitionCache, UseSpellService useSpellService, SpellDefinitionCache spellDefinitionCache, PercentageCalculator percentageCalculator) {
        super(useItemService, itemDefinitionCache, useSpellService, spellDefinitionCache);

        this.percentageCalculator = percentageCalculator;
    }

    @Override
    public void executeAction(CombatResult result, Combat combat, CombatSettingsEntity combatSettingsEntity) {
        if (percentageCalculator.calculatePercentage(combat.getUserCombatEntity().getActualMana(), combat.getUserCombatEntity().getMaximumMana()) < combatSettingsEntity.getTarget()) {
            executeCombatSettings(combat.getUserCombatEntity(), combatSettingsEntity, null);
        }
    }
}
