package com.morethanheroic.swords.map.service.task;

import com.morethanheroic.swords.map.domain.SpawnEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpawnChanceCalculator {

    private Random randomGenerator = new Random();
    private ArrayList<Integer> spawnChances = new ArrayList<>();

    public SpawnChanceCalculator(List<SpawnEntity> spawnEntityList) {
        for (SpawnEntity spawnEntity : spawnEntityList) {
            for (int i = 0; i < spawnEntity.getChance(); i++) {
                spawnChances.add(spawnEntity.getMonsterId());
            }
        }
    }

    public int getNextMonsterToSpawn() {
        return  spawnChances.get(randomGenerator.nextInt(spawnChances.size()));
    }
}
