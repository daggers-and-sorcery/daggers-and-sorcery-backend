package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.effect.domain.EffectSetting;
import com.morethanheroic.swords.effect.domain.EffectSettingHolder;

public abstract class CombatEffect {

    private final EffectSettingHolder effectSettingHolder;

    public CombatEffect(EffectSettingHolder effectSettingHolder) {
        this.effectSettingHolder = effectSettingHolder;
    }

    public EffectSetting getEffectSetting(String name) {
        return effectSettingHolder.getSetting(name);
    }

    public abstract void apply(CombatEntity combatEntity);
}
