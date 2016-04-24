package com.morethanheroic.swords.combat.service.executor.action;

import com.morethanheroic.math.PercentageCalculator;
import com.morethanheroic.swords.combat.service.UseItemService;
import com.morethanheroic.swords.combat.service.executor.action.impl.HealthCombatSettingsActionHandler;
import com.morethanheroic.swords.combat.service.executor.action.impl.ManaCombatSettingsActionHandler;
import com.morethanheroic.swords.combat.service.executor.action.impl.MonsterCombatSettingsActionHandler;
import com.morethanheroic.swords.combat.service.executor.action.impl.TurnCombatSettingsActionHandler;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.settings.model.TriggerType;
import com.morethanheroic.swords.spell.service.UseSpellService;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;

@Service
public class CombatSettingsActionHandlerFactory {

    private final Map<TriggerType, CombatSettingsActionHandler> combatSettingsActionMap = new EnumMap<>(TriggerType.class);

    //TODO: add messages to everywhere, convert all *CombatSettings to *CombatSettingsHandler and autowire them as
    //lists and build the enum map from the list post construct.
    @Autowired
    public CombatSettingsActionHandlerFactory(UseItemService useItemService, ItemDefinitionCache itemDefinitionCache, UseSpellService useSpellService, SpellDefinitionCache spellDefinitionCache, PercentageCalculator percentageCalculator, HealthCombatSettingsActionHandler healthCombatSettingsActionHandler) {
        combatSettingsActionMap.put(TriggerType.MONSTER, new MonsterCombatSettingsActionHandler(useItemService, itemDefinitionCache, useSpellService, spellDefinitionCache));
        combatSettingsActionMap.put(TriggerType.MANA, new ManaCombatSettingsActionHandler(useItemService, itemDefinitionCache, useSpellService, spellDefinitionCache, percentageCalculator));
        combatSettingsActionMap.put(TriggerType.HEALTH, healthCombatSettingsActionHandler);
        combatSettingsActionMap.put(TriggerType.TURN, new TurnCombatSettingsActionHandler(useItemService, itemDefinitionCache, useSpellService, spellDefinitionCache));
    }

    public CombatSettingsActionHandler getActionForTrigger(TriggerType triggerType) {
        return combatSettingsActionMap.get(triggerType);
    }
}
