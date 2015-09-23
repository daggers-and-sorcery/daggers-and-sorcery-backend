package com.morethanheroic.swords.combatsettings.repository.dao;

import com.morethanheroic.swords.combatsettings.model.SettingType;
import com.morethanheroic.swords.combatsettings.model.TriggerType;

public class CombatSettingsDatabaseEntity {

    private int id;
    private int user_id;
    private SettingType settings_type;
    private int settings_id;
    private TriggerType trigger_type;
    private int target;

    public CombatSettingsDatabaseEntity() {
    }

    public CombatSettingsDatabaseEntity(int userId, SettingType settings_type, int settingsId, TriggerType trigger, int target) {
        this.user_id = userId;
        this.settings_type = settings_type;
        this.settings_id = settingsId;
        this.trigger_type = trigger;
        this.target = target;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return user_id;
    }

    public SettingType getType() {
        return settings_type;
    }

    public int getSettingsId() {
        return settings_id;
    }

    public TriggerType getTrigger() {
        return trigger_type;
    }

    public int getTarget() {
        return target;
    }
}
