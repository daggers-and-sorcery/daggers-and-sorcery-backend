package com.morethanheroic.swords.combat.service.calc;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomUtil {

    private final Random random = new Random();

    public int calculateWithRandomResult(int value) {
        int thirtyPercent = (int) Math.floor(value * 0.3);
        int result = random.nextInt(value - thirtyPercent) + thirtyPercent;

        return result > 0 ? result : 1;
    }
}
