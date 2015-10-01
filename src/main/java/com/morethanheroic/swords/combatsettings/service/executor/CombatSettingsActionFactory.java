package com.morethanheroic.swords.combatsettings.service.executor;

import com.morethanheroic.swords.combatsettings.model.TriggerType;
import com.morethanheroic.swords.combatsettings.service.executor.action.HealthCombatSettings;
import com.morethanheroic.swords.combatsettings.service.executor.action.ManaCombatSettings;
import com.morethanheroic.swords.combatsettings.service.executor.action.MonsterCombatSettings;
import com.morethanheroic.swords.combatsettings.service.executor.action.TurnCombatSettings;
import com.morethanheroic.swords.item.service.UseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;

@Service
public class CombatSettingsActionFactory {

    private final EnumMap<TriggerType, CombatSettingsAction> combatSettingsActionMap = new EnumMap<>(TriggerType.class);

    @Autowired
    public CombatSettingsActionFactory(UseItemService useItemService) {
        combatSettingsActionMap.put(TriggerType.MONSTER, new MonsterCombatSettings(useItemService));
        combatSettingsActionMap.put(TriggerType.MANA, new ManaCombatSettings(useItemService));
        combatSettingsActionMap.put(TriggerType.HEALTH, new HealthCombatSettings(useItemService));
        combatSettingsActionMap.put(TriggerType.TURN, new TurnCombatSettings(useItemService));
    }

    public CombatSettingsAction getActionForTrigger(TriggerType triggerType) {
        return combatSettingsActionMap.get(triggerType);
    }
}
