package com.morethanheroic.swords.combatsettings.service;

import com.morethanheroic.swords.combatsettings.repository.dao.CombatSettingsDatabaseEntity;
import com.morethanheroic.swords.combatsettings.service.domain.CombatSettingsEntity;
import org.springframework.stereotype.Service;

@Service
public class CombatSettingsConverter {

    public CombatSettingsEntity convertCombatSettingsDatabaseEntity(CombatSettingsDatabaseEntity combatSettingsDatabaseEntity) {
        return new CombatSettingsEntity(combatSettingsDatabaseEntity.getId(), combatSettingsDatabaseEntity.getType(), combatSettingsDatabaseEntity.getSettingsId(), combatSettingsDatabaseEntity.getTrigger(), combatSettingsDatabaseEntity.getTarget());
    }
}
