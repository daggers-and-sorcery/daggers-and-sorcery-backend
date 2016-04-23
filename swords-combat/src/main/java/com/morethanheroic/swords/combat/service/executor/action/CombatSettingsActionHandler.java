package com.morethanheroic.swords.combat.service.executor.action;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.executor.action.resolver.CombatSettingsActionHandlerResolverProvider;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public abstract class CombatSettingsActionHandler {

    private final CombatSettingsActionHandlerResolverProvider combatSettingsActionHandlerResolverProvider;

    public abstract void executeAction(CombatResult result, Combat combat, CombatSettingsEntity combatSettingsEntity);

    protected void executeCombatSettings(UserCombatEntity userEntity, CombatSettingsEntity combatSettingsEntity, CombatEffectDataHolder combatEffectDataHolder) {
        log.debug("Running combat settings: " + combatSettingsEntity);

        combatSettingsActionHandlerResolverProvider.getResolver(combatSettingsEntity.getType()).handleSettings(userEntity, combatSettingsEntity, combatEffectDataHolder);
    }
}
