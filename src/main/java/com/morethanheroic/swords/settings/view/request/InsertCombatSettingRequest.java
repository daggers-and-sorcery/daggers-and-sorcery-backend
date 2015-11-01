package com.morethanheroic.swords.settings.view.request;

import com.morethanheroic.swords.settings.model.SettingType;
import com.morethanheroic.swords.settings.model.TriggerType;

public class InsertCombatSettingRequest {

    private SettingType type;
    private TriggerType trigger;
    private int use;
    private int target;

    public SettingType getType() {
        return type;
    }

    public void setType(SettingType type) {
        this.type = type;
    }

    public TriggerType getTrigger() {
        return trigger;
    }

    public void setTrigger(TriggerType trigger) {
        this.trigger = trigger;
    }

    public int getUse() {
        return use;
    }

    public void setUse(int use) {
        this.use = use;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}
