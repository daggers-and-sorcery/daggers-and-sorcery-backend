package com.morethanheroic.swords.combatsettings.service.executor.action;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combatsettings.service.domain.CombatSettingsEntity;
import com.morethanheroic.swords.combatsettings.service.executor.CombatSettingsAction;
import com.morethanheroic.swords.item.service.UseItemService;

public class MonsterCombatSettings extends CombatSettingsAction {

    public MonsterCombatSettings(UseItemService useItemService) {
        super(useItemService);
    }

    @Override
    public void executeAction(CombatResult result, Combat combat, CombatSettingsEntity combatSettingsEntity) {
        if (combat.getMonsterCombatEntity().getMonsterDefinition().getId() == combatSettingsEntity.getTarget()) {
            executeCombatSettings(combat.getUserCombatEntity().getUserEntity(), combatSettingsEntity);
        }
    }
}
