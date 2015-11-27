package com.morethanheroic.swords.combat.service.calc.scavenge.domain;

import com.morethanheroic.swords.combat.domain.ScavengingResultEntity;

import java.util.List;

public class ScavengingResult {

    private final List<ScavengingResultEntity> scavengingResultEntityList;

    public ScavengingResult(List<ScavengingResultEntity> scavengingResultEntityList) {
        this.scavengingResultEntityList = scavengingResultEntityList;
    }

    public List<ScavengingResultEntity> getScavengingResultList() {
        return scavengingResultEntityList;
    }

    public boolean isSuccessfullScavenge() {
        return scavengingResultEntityList.size() > 0;
    }
}
