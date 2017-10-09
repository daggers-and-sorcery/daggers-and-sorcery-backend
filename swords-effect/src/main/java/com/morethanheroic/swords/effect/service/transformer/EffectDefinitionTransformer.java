package com.morethanheroic.swords.effect.service.transformer;

import com.morethanheroic.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinition;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;
import com.morethanheroic.swords.effect.service.domain.RawEffectDefinition;
import com.morethanheroic.swords.effect.service.domain.RawEffectSetting;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Create effects from the freshly loaded xml data.
 */
@Service
public class EffectDefinitionTransformer implements DefinitionTransformer<EffectSettingDefinitionHolder, RawEffectDefinition> {

    /**
     * Transform a {@link RawEffectDefinition} into a {@link EffectSettingDefinitionHolder}.
     *
     * @param rawEffectDefinition the definition we are transforming
     * @return the result of the transformation
     */
    public EffectSettingDefinitionHolder transform(RawEffectDefinition rawEffectDefinition) {
        final Map<String, EffectSettingDefinition> settings = new HashMap<>();

        if (rawEffectDefinition.getEffectSettings() != null) {
            for (RawEffectSetting effectSetting : rawEffectDefinition.getEffectSettings()) {
                settings.put(effectSetting.getName(), new EffectSettingDefinition(effectSetting.getName(), effectSetting.getValue()));
            }
        }

        return EffectSettingDefinitionHolder.builder()
                .effectId(rawEffectDefinition.getTarget())
                .settings(settings)
                .build();
    }
}
