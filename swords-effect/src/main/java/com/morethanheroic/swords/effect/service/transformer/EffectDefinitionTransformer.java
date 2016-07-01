package com.morethanheroic.swords.effect.service.transformer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinition;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;
import com.morethanheroic.swords.effect.service.domain.RawEffectDefinition;
import com.morethanheroic.swords.effect.service.domain.RawEffectSetting;

/**
 * Create effects from the freshly loaded xml data.
 */
@Service
public class EffectDefinitionTransformer implements DefinitionTransformer<EffectSettingDefinitionHolder, RawEffectDefinition> {

    public EffectSettingDefinitionHolder transform(RawEffectDefinition rawEffectDefinition) {
        final Map<String, EffectSettingDefinition> settings = new HashMap<>();

        if (rawEffectDefinition.getEffectSettings() != null) {
            for (RawEffectSetting effectSetting : rawEffectDefinition.getEffectSettings()) {
                settings.put(effectSetting.getName(), new EffectSettingDefinition(effectSetting.getName(), effectSetting.getValue()));
            }
        }

        return new EffectSettingDefinitionHolder(rawEffectDefinition.getTarget(), settings);
    }
}
