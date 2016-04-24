package com.morethanheroic.swords.combat.service.executor.action.impl;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.UseItemService;
import com.morethanheroic.swords.combat.service.executor.action.resolver.CombatSettingsActionHandlerResolverProvider;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;
import com.morethanheroic.swords.combat.service.executor.action.CombatSettingsActionHandler;
import com.morethanheroic.swords.spell.service.UseSpellService;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import org.springframework.stereotype.Service;

@Service
public class MonsterCombatSettingsActionHandler extends CombatSettingsActionHandler {

    public MonsterCombatSettingsActionHandler(CombatSettingsActionHandlerResolverProvider combatSettingsActionHandlerResolverProvider) {
        super(combatSettingsActionHandlerResolverProvider);
    }

    @Override
    public void executeAction(CombatResult result, Combat combat, CombatSettingsEntity combatSettingsEntity) {
        if (combat.getMonsterCombatEntity().getMonsterDefinition().getId() == combatSettingsEntity.getTarget()) {
            executeCombatSettings(combat.getUserCombatEntity(), result, combatSettingsEntity);
        }
    }
}
