package com.morethanheroic.swords.settings.service.executor.action;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;
import com.morethanheroic.swords.settings.service.executor.CombatSettingsAction;
import com.morethanheroic.swords.item.service.ItemDefinitionCache;
import com.morethanheroic.swords.item.service.UseItemService;
import com.morethanheroic.swords.spell.service.SpellDefinitionManager;
import com.morethanheroic.swords.spell.service.domain.UseSpellService;

public class TurnCombatSettings extends CombatSettingsAction {

    public TurnCombatSettings(UseItemService useItemService, ItemDefinitionCache itemDefinitionCache, UseSpellService useSpellService, SpellDefinitionManager spellDefinitionManager) {
        super(useItemService, itemDefinitionCache, useSpellService, spellDefinitionManager);
    }

    @Override
    public void executeAction(CombatResult result, Combat combat, CombatSettingsEntity combatSettingsEntity) {
        if (combat.getTurn() == combatSettingsEntity.getTarget()) {
            executeCombatSettings(combat.getUserCombatEntity(), combatSettingsEntity);
        }
    }
}
