package com.morethanheroic.swords.combatsettings.service.executor.action;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combatsettings.service.domain.CombatSettingsEntity;
import com.morethanheroic.swords.combatsettings.service.executor.CombatSettingsAction;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.item.service.UseItemService;
import com.morethanheroic.swords.spell.service.SpellDefinitionManager;
import com.morethanheroic.swords.spell.service.domain.UseSpellService;

public class MonsterCombatSettings extends CombatSettingsAction {

    public MonsterCombatSettings(UseItemService useItemService, ItemDefinitionManager itemDefinitionManager, UseSpellService useSpellService, SpellDefinitionManager spellDefinitionManager) {
        super(useItemService, itemDefinitionManager, useSpellService, spellDefinitionManager);
    }

    @Override
    public void executeAction(CombatResult result, Combat combat, CombatSettingsEntity combatSettingsEntity) {
        if (combat.getMonsterCombatEntity().getMonsterDefinition().getId() == combatSettingsEntity.getTarget()) {
            executeCombatSettings(combat.getUserCombatEntity(), combatSettingsEntity);
        }
    }
}
