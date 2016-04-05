package com.morethanheroic.swords.combat.domain.effect;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatEffectServiceAccessor;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.effect.domain.EffectDefinition;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinition;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;

public abstract class CombatEffectDefinition implements EffectDefinition {

    private final EffectSettingDefinitionHolder effectSettingDefinitionHolder;

    public CombatEffectDefinition(EffectSettingDefinitionHolder effectSettingDefinitionHolder) {
        this.effectSettingDefinitionHolder = effectSettingDefinitionHolder;
    }

    public EffectSettingDefinition getEffectSetting(String name) {
        return effectSettingDefinitionHolder.getSetting(name);
    }

    public abstract void apply(CombatEntity combatEntity, CombatEffectDataHolder combatEffectDataHolder, CombatEffectServiceAccessor combatEffectServiceAccessor);
}
