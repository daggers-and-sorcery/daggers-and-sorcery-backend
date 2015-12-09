package com.morethanheroic.swords.effect.service;

import com.morethanheroic.swords.effect.domain.Effect;
import com.morethanheroic.swords.effect.domain.EffectSetting;
import com.morethanheroic.swords.effect.domain.EffectSettingHolder;
import com.morethanheroic.swords.effect.exception.InvalidEffectException;
import com.morethanheroic.swords.effect.service.domain.RawEffect;
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
public class EffectTransformer {

    public Effect transform(RawEffect rawEffect) throws InvalidEffectException {
        try {
            final Map<String, EffectSetting> settings = new HashMap<>();

            if (rawEffect.getEffectSettings() != null) {
                for (RawEffectSetting effectSetting : rawEffect.getEffectSettings()) {
                    settings.put(effectSetting.getName(), new EffectSetting(effectSetting.getName(), effectSetting.getValue()));
                }
            }

            return createNewEffect(rawEffect.getTarget(), settings);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException ex) {
            throw new InvalidEffectException("Effect " + rawEffect.toString() + " can't be transformed.", ex);
        }
    }

    private Effect createNewEffect(String effect, Map<String, EffectSetting> settings) throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (Effect) getConstructorForEffect(effect).newInstance(new EffectSettingHolder(settings));
    }

    private Constructor getConstructorForEffect(String effect) throws ClassNotFoundException, NoSuchMethodException {
        return Class.forName(effect).getConstructor(EffectSettingHolder.class);
    }
}
