package com.morethanheroic.swords.combat.domain.effect;

import com.morethanheroic.swords.dice.domain.DiceRollCalculationContext;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;
import org.springframework.stereotype.Service;

@Service
public class DiceRollFromDamageSettingsBuilder {

    public DiceRollCalculationContext buildDiceRollCalculationContext(EffectSettingDefinitionHolder effectSettingDefinitionHolder) {
        return DiceRollCalculationContext.builder()
                .value(effectSettingDefinitionHolder.getSettingAsInt("value"))
                .d2(effectSettingDefinitionHolder.getSettingAsInt("d2"))
                .d4(effectSettingDefinitionHolder.getSettingAsInt("d4"))
                .d6(effectSettingDefinitionHolder.getSettingAsInt("d6"))
                .d8(effectSettingDefinitionHolder.getSettingAsInt("d8"))
                .d10(effectSettingDefinitionHolder.getSettingAsInt("d10"))
                .build();
    }
}
