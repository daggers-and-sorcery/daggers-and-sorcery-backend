package com.morethanheroic.swords.combat.service.executor.action.handler;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.UseItemService;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;
import com.morethanheroic.swords.combat.service.executor.action.CombatSettingsActionHandler;
import com.morethanheroic.swords.spell.service.UseSpellService;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;

public class TurnCombatSettingsHandler extends CombatSettingsActionHandler {

    public TurnCombatSettingsHandler(UseItemService useItemService, ItemDefinitionCache itemDefinitionCache, UseSpellService useSpellService, SpellDefinitionCache spellDefinitionCache) {
        super(useItemService, itemDefinitionCache, useSpellService, spellDefinitionCache);
    }

    @Override
    public void executeAction(CombatResult result, Combat combat, CombatSettingsEntity combatSettingsEntity) {
        if (combat.getTurn() == combatSettingsEntity.getTarget()) {
            executeCombatSettings(combat.getUserCombatEntity(), combatSettingsEntity, null);
        }
    }
}
