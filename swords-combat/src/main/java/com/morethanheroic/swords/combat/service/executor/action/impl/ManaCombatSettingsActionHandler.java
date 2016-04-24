package com.morethanheroic.swords.combat.service.executor.action.impl;

import com.morethanheroic.math.PercentageCalculator;
import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.executor.action.resolver.CombatSettingsActionHandlerResolverProvider;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;
import com.morethanheroic.swords.combat.service.executor.action.CombatSettingsActionHandler;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.combat.service.UseItemService;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import com.morethanheroic.swords.spell.service.UseSpellService;
import org.springframework.stereotype.Service;

@Service
public class ManaCombatSettingsActionHandler extends CombatSettingsActionHandler {

    private final PercentageCalculator percentageCalculator;

    public ManaCombatSettingsActionHandler(CombatSettingsActionHandlerResolverProvider combatSettingsActionHandlerResolverProvider, PercentageCalculator percentageCalculator) {
        super(combatSettingsActionHandlerResolverProvider);

        this.percentageCalculator = percentageCalculator;
    }

    @Override
    public void executeAction(CombatResult combatResult, Combat combat, CombatSettingsEntity combatSettingsEntity) {
        if (percentageCalculator.calculatePercentage(combat.getUserCombatEntity().getActualMana(), combat.getUserCombatEntity().getMaximumMana()) < combatSettingsEntity.getTarget()) {
            executeCombatSettings(combat.getUserCombatEntity(), combatResult, combatSettingsEntity);
        }
    }
}
