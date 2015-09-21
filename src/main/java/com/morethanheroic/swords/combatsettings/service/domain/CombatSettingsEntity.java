package com.morethanheroic.swords.combatsettings.service.domain;

import com.morethanheroic.swords.combatsettings.model.SettingType;
import com.morethanheroic.swords.combatsettings.model.TriggerType;

public class CombatSettingsEntity {

    private int id;
    private int userId;
    private SettingType type;
    private int settingsId;
    private TriggerType trigger;
    private int target;

    public CombatSettingsEntity(int userId, SettingType type, int settingsId, TriggerType trigger, int target) {
        this.userId = userId;
        this.type = type;
        this.settingsId = settingsId;
        this.trigger = trigger;
        this.target = target;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public SettingType getType() {
        return type;
    }

    public int getSettingsId() {
        return settingsId;
    }

    public TriggerType getTrigger() {
        return trigger;
    }

    public int getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "CombatSettingsEntity -> [id: " + id + " userId: " + userId + " type: " + type + " settingsId: " + settingsId + " userId: " + trigger + " target: " + target + "]";
    }
}
