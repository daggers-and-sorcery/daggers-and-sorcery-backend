package com.morethanheroic.swords.combat.service.calc.scavenge.domain;

import com.morethanheroic.swords.combat.domain.ScavengingResultEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ScavengingResult {

    private final List<ScavengingResultEntity> scavengingResultList;
    private final int scavengingXp;

    public boolean isSuccessfulScavenge() {
        return scavengingResultList.size() > 0;
    }
}
