package com.morethanheroic.swords.settings.service;

import com.morethanheroic.swords.settings.repository.dao.CombatSettingsDatabaseEntity;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;
import org.springframework.stereotype.Service;

@Service
public class CombatSettingsConverter {

    public CombatSettingsEntity convertCombatSettingsDatabaseEntity(CombatSettingsDatabaseEntity combatSettingsDatabaseEntity) {
        return new CombatSettingsEntity(combatSettingsDatabaseEntity.getId(), combatSettingsDatabaseEntity.getType(), combatSettingsDatabaseEntity.getSettingsId(), combatSettingsDatabaseEntity.getTrigger(), combatSettingsDatabaseEntity.getTarget());
    }
}
