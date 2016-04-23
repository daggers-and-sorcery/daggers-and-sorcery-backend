package com.morethanheroic.swords.combat.service.executor;

import com.morethanheroic.math.PercentageCalculator;
import com.morethanheroic.swords.combat.service.UseItemService;
import com.morethanheroic.swords.combat.service.executor.action.HealthCombatSettingsHandler;
import com.morethanheroic.swords.combat.service.executor.action.ManaCombatSettings;
import com.morethanheroic.swords.combat.service.executor.action.MonsterCombatSettings;
import com.morethanheroic.swords.combat.service.executor.action.TurnCombatSettings;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.settings.model.TriggerType;
import com.morethanheroic.swords.spell.service.UseSpellService;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;

@Service
public class CombatSettingsActionFactory {

    private final EnumMap<TriggerType, CombatSettingsAction> combatSettingsActionMap = new EnumMap<>(TriggerType.class);

    //TODO: add messages to everywhere, convert all *CombatSettings to *CombatSettingsHandler and autowire them as
    //lists and build the enum map from the list post construct.
    @Autowired
    public CombatSettingsActionFactory(UseItemService useItemService, ItemDefinitionCache itemDefinitionCache, UseSpellService useSpellService, SpellDefinitionCache spellDefinitionCache, PercentageCalculator percentageCalculator, HealthCombatSettingsHandler healthCombatSettingsHandler) {
        combatSettingsActionMap.put(TriggerType.MONSTER, new MonsterCombatSettings(useItemService, itemDefinitionCache, useSpellService, spellDefinitionCache));
        combatSettingsActionMap.put(TriggerType.MANA, new ManaCombatSettings(useItemService, itemDefinitionCache, useSpellService, spellDefinitionCache, percentageCalculator));
        combatSettingsActionMap.put(TriggerType.HEALTH, healthCombatSettingsHandler);
        combatSettingsActionMap.put(TriggerType.TURN, new TurnCombatSettings(useItemService, itemDefinitionCache, useSpellService, spellDefinitionCache));
    }

    public CombatSettingsAction getActionForTrigger(TriggerType triggerType) {
        return combatSettingsActionMap.get(triggerType);
    }
}
