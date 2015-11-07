package com.morethanheroic.swords.regeneration.service;

import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class RegenerationDateCalculator {

    private static final int REGENERATION_INTERVAL = 5;

    public int calculatePassedDurationSinceLastRegeneration(long lastRegenerationTime) {
        long durationSinceLastRegeneration = Instant.now().toEpochMilli() - lastRegenerationTime;
        int passedMinutesSinceLastRegeneration = (int) TimeUnit.MILLISECONDS.toMinutes(durationSinceLastRegeneration);

        return (int) Math.floor(passedMinutesSinceLastRegeneration / REGENERATION_INTERVAL);
    }

    public Date calculateNewRegenerationDate(UserEntity user, int duration) {
        return new Date(user.getLastRegenerationDate().getTime() + TimeUnit.MINUTES.toMillis(duration * 5));
    }
}
