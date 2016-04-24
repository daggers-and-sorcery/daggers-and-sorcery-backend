package com.morethanheroic.swords.combat.service.executor.action;

import com.morethanheroic.swords.settings.model.TriggerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class CombatSettingsActionHandlerProvider {

    private final Map<TriggerType, CombatSettingsActionHandler> combatSettingsActionMap = new EnumMap<>(TriggerType.class);

    @Autowired
    public CombatSettingsActionHandlerProvider(List<CombatSettingsActionHandler> combatSettingsActionHandlers) {
        for (CombatSettingsActionHandler combatSettingsActionHandler : combatSettingsActionHandlers) {
            combatSettingsActionMap.put(combatSettingsActionHandler.getTriggerType(), combatSettingsActionHandler);
        }
    }

    public CombatSettingsActionHandler getActionForTrigger(TriggerType triggerType) {
        Assert.isTrue(combatSettingsActionMap.containsKey(triggerType));

        return combatSettingsActionMap.get(triggerType);
    }
}
