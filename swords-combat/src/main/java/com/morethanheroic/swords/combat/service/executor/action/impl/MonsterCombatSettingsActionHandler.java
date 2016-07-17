package com.morethanheroic.swords.combat.service.executor.action.impl;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.UseItemService;
import com.morethanheroic.swords.combat.service.executor.action.resolver.CombatSettingsActionHandlerResolverProvider;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.settings.model.TriggerType;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;
import com.morethanheroic.swords.combat.service.executor.action.CombatSettingsActionHandler;
import com.morethanheroic.swords.spell.service.UseSpellService;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonsterCombatSettingsActionHandler extends CombatSettingsActionHandler {

    private final CombatMessageBuilder combatMessageBuilder;

    @Autowired
    public MonsterCombatSettingsActionHandler(CombatSettingsActionHandlerResolverProvider combatSettingsActionHandlerResolverProvider, CombatMessageBuilder combatMessageBuilder) {
        super(combatSettingsActionHandlerResolverProvider);

        this.combatMessageBuilder = combatMessageBuilder;
    }

    @Override
    public void executeAction(CombatResult combatResult, Combat combat, CombatSettingsEntity combatSettingsEntity) {
        final MonsterDefinition monsterDefinition =  combat.getMonsterCombatEntity().getMonsterDefinition();

        if (monsterDefinition.getId() == combatSettingsEntity.getTarget()) {
            combatResult.addMessage(combatMessageBuilder.buildMonsterSettingTriggeredMessage(monsterDefinition.getName()));

            executeCombatSettings(combat, combatResult, combatSettingsEntity);
        }
    }

    @Override
    public TriggerType getTriggerType() {
        return TriggerType.MONSTER;
    }
}
