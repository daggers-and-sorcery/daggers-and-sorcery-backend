package com.morethanheroic.swords.combat.service.executor.action.handler;

import com.morethanheroic.math.PercentageCalculator;
import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.UseItemService;
import com.morethanheroic.swords.combat.service.executor.action.CombatSettingsActionHandler;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;
import com.morethanheroic.swords.spell.service.UseSpellService;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthCombatSettingsActionHandler extends CombatSettingsActionHandler {

    private final PercentageCalculator percentageCalculator;
    private final CombatMessageBuilder combatMessageBuilder;

    @Autowired
    public HealthCombatSettingsActionHandler(UseItemService useItemService, ItemDefinitionCache itemDefinitionCache, UseSpellService useSpellService, SpellDefinitionCache spellDefinitionCache, PercentageCalculator percentageCalculator, CombatMessageBuilder combatMessageBuilder) {
        super(useItemService, itemDefinitionCache, useSpellService, spellDefinitionCache);

        this.percentageCalculator = percentageCalculator;
        this.combatMessageBuilder = combatMessageBuilder;
    }

    @Override
    public void executeAction(CombatResult combatResult, Combat combat, CombatSettingsEntity combatSettingsEntity) {
        if (percentageCalculator.calculatePercentage(combat.getUserCombatEntity().getActualHealth(), combat.getUserCombatEntity().getMaximumHealth()) < combatSettingsEntity.getTarget()) {
            combatResult.addMessage(combatMessageBuilder.buildHealthSettingTriggeredMessage(combatSettingsEntity.getTarget()));

            executeCombatSettings(combat.getUserCombatEntity(), combatSettingsEntity, null);
        }
    }
}
