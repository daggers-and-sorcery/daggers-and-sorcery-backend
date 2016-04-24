package com.morethanheroic.swords.combat.service.executor;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.executor.action.CombatSettingsActionHandlerProvider;
import com.morethanheroic.swords.settings.service.CombatSettingsFacade;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombatSettingsExecutor {

    private final CombatSettingsFacade combatSettingsFacade;
    private final CombatSettingsActionHandlerProvider combatSettingsActionHandlerProvider;

    @Autowired
    public CombatSettingsExecutor(CombatSettingsFacade combatSettingsFacade, CombatSettingsActionHandlerProvider combatSettingsActionHandlerProvider) {
        this.combatSettingsFacade = combatSettingsFacade;
        this.combatSettingsActionHandlerProvider = combatSettingsActionHandlerProvider;
    }

    public void executeCombatSettings(CombatResult result, Combat combat) {
        final List<CombatSettingsEntity> combatSettingsEntityList = combatSettingsFacade.getAllCombatSettings(combat.getUserCombatEntity().getUserEntity());

        for (CombatSettingsEntity combatSettingsEntity : combatSettingsEntityList) {
            combatSettingsActionHandlerProvider.getActionForTrigger(combatSettingsEntity.getTrigger()).executeAction(result, combat, combatSettingsEntity);
        }
    }
}
