package com.morethanheroic.swords.combatsettings.repository.dao;

import com.morethanheroic.swords.combatsettings.model.SettingType;
import com.morethanheroic.swords.combatsettings.model.TriggerType;

public class CombatSettingsDatabaseEntity {

    private int id;
    private int user_id;
    private SettingType type;
    private int settings_id;
    private TriggerType trigger;
    private int target;

    public CombatSettingsDatabaseEntity(int userId, SettingType type, int settingsId, TriggerType trigger, int target) {
        this.user_id = userId;
        this.type = type;
        this.settings_id = settingsId;
        this.trigger = trigger;
        this.target = target;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return user_id;
    }

    public SettingType getType() {
        return type;
    }

    public int getSettingsId() {
        return settings_id;
    }

    public TriggerType getTrigger() {
        return trigger;
    }

    public int getTarget() {
        return target;
    }
}
