package com.morethanheroic.swords.combat.service.executor;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.settings.service.CombatSettingsFacade;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombatSettingsExecutor {

    private final CombatSettingsFacade combatSettingsFacade;
    private final CombatSettingsActionFactory combatSettingsActionFactory;

    @Autowired
    public CombatSettingsExecutor(CombatSettingsFacade combatSettingsFacade, CombatSettingsActionFactory combatSettingsActionFactory) {
        this.combatSettingsFacade = combatSettingsFacade;
        this.combatSettingsActionFactory = combatSettingsActionFactory;
    }

    public void executeCombatSettings(CombatResult result, Combat combat) {
        List<CombatSettingsEntity> combatSettingsEntityList = combatSettingsFacade.getAllCombatSettings(combat.getUserCombatEntity().getUserEntity());

        for (CombatSettingsEntity combatSettingsEntity : combatSettingsEntityList) {
            combatSettingsActionFactory.getActionForTrigger(combatSettingsEntity.getTrigger()).executeAction(result, combat, combatSettingsEntity);
        }
    }
}
