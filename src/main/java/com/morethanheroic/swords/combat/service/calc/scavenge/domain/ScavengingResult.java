package com.morethanheroic.swords.combat.service.calc.scavenge.domain;

import com.morethanheroic.swords.combat.domain.ScavengingEntity;

import java.util.List;

public class ScavengingResult {

    private final List<ScavengingEntity> scavengingEntityList;

    public ScavengingResult(List<ScavengingEntity> scavengingEntityList) {
        this.scavengingEntityList = scavengingEntityList;
    }

    public List<ScavengingEntity> getScavengingResultList() {
        return scavengingEntityList;
    }

    public boolean isSuccessfullScavenge() {
        return scavengingEntityList.size() > 0;
    }
}
