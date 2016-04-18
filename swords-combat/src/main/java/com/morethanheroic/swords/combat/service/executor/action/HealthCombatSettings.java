package com.morethanheroic.swords.combat.service.executor.action;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.UseItemService;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;
import com.morethanheroic.swords.combat.service.executor.CombatSettingsAction;
import com.morethanheroic.swords.spell.service.UseSpellService;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;

public class HealthCombatSettings extends CombatSettingsAction {

    public HealthCombatSettings(UseItemService useItemService, ItemDefinitionCache itemDefinitionCache, UseSpellService useSpellService, SpellDefinitionCache spellDefinitionCache) {
        super(useItemService, itemDefinitionCache, useSpellService, spellDefinitionCache);
    }

    @Override
    public void executeAction(CombatResult result, Combat combat, CombatSettingsEntity combatSettingsEntity) {
        //BUG: !!! This should calculate percentage not exact value!!!!
        if (combat.getUserCombatEntity().getActualHealth() < combatSettingsEntity.getTarget()) {
            executeCombatSettings(combat.getUserCombatEntity(), combatSettingsEntity, null);
        }
    }
}
