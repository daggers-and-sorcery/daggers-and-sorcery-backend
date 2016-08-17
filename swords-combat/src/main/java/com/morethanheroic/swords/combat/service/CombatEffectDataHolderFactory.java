package com.morethanheroic.swords.combat.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;

@Service
public class CombatEffectDataHolderFactory {

    public CombatEffectDataHolder newDataHolder(final SessionEntity sessionEntity) {
        return new CombatEffectDataHolder(new HashMap<>(), sessionEntity);
    }
}
