package com.morethanheroic.swords.settings.repository.dao;

import com.morethanheroic.swords.settings.model.SettingType;
import com.morethanheroic.swords.settings.model.TriggerType;

public class CombatSettingsDatabaseEntity {

    private int id;
    private int userId;
    private SettingType settingsType;
    private int settingsId;
    private TriggerType triggerType;
    private int target;

    public CombatSettingsDatabaseEntity() {
    }

    public CombatSettingsDatabaseEntity(int userId, SettingType settings_type, int settingsId, TriggerType trigger, int target) {
        this.userId = userId;
        this.settingsType = settings_type;
        this.settingsId = settingsId;
        this.triggerType = trigger;
        this.target = target;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public SettingType getType() {
        return settingsType;
    }

    public int getSettingsId() {
        return settingsId;
    }

    public TriggerType getTrigger() {
        return triggerType;
    }

    public int getTarget() {
        return target;
    }
}
