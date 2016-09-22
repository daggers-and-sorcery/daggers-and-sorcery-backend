package com.morethanheroic.swords.settings.model;

import com.morethanheroic.swords.settings.repository.dao.SettingsDatabaseEntity;

public class SettingsEntity {

    private final SettingsDatabaseEntity settingsDatabaseEntity;

    public SettingsEntity(SettingsDatabaseEntity settingsDatabaseEntity) {
        this.settingsDatabaseEntity = settingsDatabaseEntity;
    }

    public boolean isScavengingEnabled() {
        return settingsDatabaseEntity != null && settingsDatabaseEntity.isScavengingEnabled();
    }
}
