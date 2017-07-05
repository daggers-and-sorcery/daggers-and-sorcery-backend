package com.morethanheroic.swords.statuseffect.service;

import com.morethanheroic.swords.statuseffect.repository.domain.StatusEffectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Remove expired status effects.
 */
@Service
@RequiredArgsConstructor
public class StatusEffectCleaner {

    private static final int CLEANING_RATE_IN_MILLISECONDS = 100;

    private final StatusEffectMapper statusEffectMapper;

    /**
     * Remove expired status effects.
     */
    @Transactional
    @Scheduled(fixedRate = CLEANING_RATE_IN_MILLISECONDS)
    public void cleanStatusEffects() {
        statusEffectMapper.removeExpiredStatusEffects();
    }
}
