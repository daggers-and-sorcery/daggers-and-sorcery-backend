package com.morethanheroic.swords.effect.service;

import com.morethanheroic.swords.combat.domain.CombatEffect;
import com.morethanheroic.swords.effect.domain.EffectSetting;
import com.morethanheroic.swords.effect.domain.EffectSettingHolder;
import com.morethanheroic.swords.effect.service.domain.RawEffect;
import com.morethanheroic.swords.effect.service.domain.RawEffectSetting;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

@Service
public class EffectDefinitionBuilder {

    public CombatEffect build(RawEffect rawEffect) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        HashMap<String, EffectSetting> settings = new HashMap<>();

        for (RawEffectSetting effectSetting : rawEffect.getEffectSettings()) {
            settings.put(effectSetting.getName(), new EffectSetting(effectSetting.getName(), effectSetting.getValue()));
        }

        return (CombatEffect) Class.forName(rawEffect.getTarget()).getConstructor(EffectSettingHolder.class).newInstance(new EffectSettingHolder(settings));
    }
}
