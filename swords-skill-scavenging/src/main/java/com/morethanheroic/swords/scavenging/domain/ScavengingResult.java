package com.morethanheroic.swords.scavenging.domain;

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
