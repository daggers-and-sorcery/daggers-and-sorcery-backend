package com.morethanheroic.swords.effect.service;

import com.morethanheroic.swords.effect.domain.Effect;
import com.morethanheroic.swords.effect.domain.EffectSetting;
import com.morethanheroic.swords.effect.domain.EffectSettingHolder;
import com.morethanheroic.swords.effect.service.domain.RawEffect;
import com.morethanheroic.swords.effect.service.domain.RawEffectSetting;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

@Service
public class EffectTransformer {

    public Effect build(RawEffect rawEffect) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        HashMap<String, EffectSetting> settings = new HashMap<>();

        if (rawEffect.getEffectSettings() != null) {
            for (RawEffectSetting effectSetting : rawEffect.getEffectSettings()) {
                settings.put(effectSetting.getName(), new EffectSetting(effectSetting.getName(), effectSetting.getValue()));
            }
        }

        return (Effect) Class.forName(rawEffect.getTarget()).getConstructor(EffectSettingHolder.class).newInstance(new EffectSettingHolder(settings));
    }
}
