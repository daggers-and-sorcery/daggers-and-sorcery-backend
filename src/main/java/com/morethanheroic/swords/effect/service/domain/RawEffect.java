package com.morethanheroic.swords.effect.service.domain;

import com.morethanheroic.swords.item.service.domain.ItemEffectSetting;

import java.util.ArrayList;

public abstract class RawEffect {

    public abstract String getTarget();

    public abstract ArrayList<ItemEffectSetting> getEffectSettings();
}
