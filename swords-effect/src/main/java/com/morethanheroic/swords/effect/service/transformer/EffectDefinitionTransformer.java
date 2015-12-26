package com.morethanheroic.swords.effect.service.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.effect.domain.EffectDefinition;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinition;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;
import com.morethanheroic.swords.effect.exception.InvalidEffectException;
import com.morethanheroic.swords.effect.service.domain.RawEffectDefinition;
import com.morethanheroic.swords.effect.service.domain.RawEffectSetting;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Create effects from the freshly loaded xml data.
 */
@Service
public class EffectDefinitionTransformer implements DefinitionTransformer<EffectDefinition, RawEffectDefinition> {

    public EffectDefinition transform(RawEffectDefinition rawEffectDefinition) {
        try {
            final Map<String, EffectSettingDefinition> settings = new HashMap<>();

            if (rawEffectDefinition.getEffectSettings() != null) {
                for (RawEffectSetting effectSetting : rawEffectDefinition.getEffectSettings()) {
                    settings.put(effectSetting.getName(), new EffectSettingDefinition(effectSetting.getName(), effectSetting.getValue()));
                }
            }

            return createNewEffect(rawEffectDefinition.getTarget(), settings);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException ex) {
            throw new InvalidEffectException("Effect " + rawEffectDefinition.toString() + " can't be transformed.", ex);
        }
    }

    private EffectDefinition createNewEffect(String effect, Map<String, EffectSettingDefinition> settings) throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (EffectDefinition) getConstructorForEffect(effect).newInstance(new EffectSettingDefinitionHolder(settings));
    }

    private Constructor getConstructorForEffect(String effect) throws ClassNotFoundException, NoSuchMethodException {
        return Class.forName(effect).getConstructor(EffectSettingDefinitionHolder.class);
    }
}
