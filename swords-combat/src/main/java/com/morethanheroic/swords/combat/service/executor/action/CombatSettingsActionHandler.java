package com.morethanheroic.swords.combat.service.executor.action;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.executor.action.resolver.CombatSettingsActionHandlerResolverProvider;
import com.morethanheroic.swords.settings.model.TriggerType;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor
public abstract class CombatSettingsActionHandler {

    private final CombatSettingsActionHandlerResolverProvider combatSettingsActionHandlerResolverProvider;

    public abstract void executeAction(CombatResult result, Combat combat, CombatSettingsEntity combatSettingsEntity);

    public abstract TriggerType getTriggerType();

    protected void executeCombatSettings(UserCombatEntity userEntity, CombatResult combatResult, CombatSettingsEntity combatSettingsEntity) {
        log.debug("Running combat settings: " + combatSettingsEntity);

        combatSettingsActionHandlerResolverProvider.getResolver(combatSettingsEntity.getType()).handleSettings(userEntity, combatResult, combatSettingsEntity, null);
    }
}
