package com.morethanheroic.swords.settings.service.executor;

import com.morethanheroic.swords.settings.model.TriggerType;
import com.morethanheroic.swords.settings.service.executor.action.HealthCombatSettings;
import com.morethanheroic.swords.settings.service.executor.action.ManaCombatSettings;
import com.morethanheroic.swords.settings.service.executor.action.MonsterCombatSettings;
import com.morethanheroic.swords.settings.service.executor.action.TurnCombatSettings;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.item.service.UseItemService;
import com.morethanheroic.swords.spell.service.SpellDefinitionManager;
import com.morethanheroic.swords.spell.service.domain.UseSpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;

@Service
public class CombatSettingsActionFactory {

    private final EnumMap<TriggerType, CombatSettingsAction> combatSettingsActionMap = new EnumMap<>(TriggerType.class);

    @Autowired
    public CombatSettingsActionFactory(UseItemService useItemService, ItemDefinitionCache itemDefinitionCache, UseSpellService useSpellService, SpellDefinitionManager spellDefinitionManager) {
        combatSettingsActionMap.put(TriggerType.MONSTER, new MonsterCombatSettings(useItemService, itemDefinitionCache, useSpellService, spellDefinitionManager));
        combatSettingsActionMap.put(TriggerType.MANA, new ManaCombatSettings(useItemService, itemDefinitionCache, useSpellService, spellDefinitionManager));
        combatSettingsActionMap.put(TriggerType.HEALTH, new HealthCombatSettings(useItemService, itemDefinitionCache, useSpellService, spellDefinitionManager));
        combatSettingsActionMap.put(TriggerType.TURN, new TurnCombatSettings(useItemService, itemDefinitionCache, useSpellService, spellDefinitionManager));
    }

    public CombatSettingsAction getActionForTrigger(TriggerType triggerType) {
        return combatSettingsActionMap.get(triggerType);
    }
}
