package com.morethanheroic.swords.monster.service;

import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.service.loader.MonsterDefinitionLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Service
public class MonsterDefinitionCache {

    private final MonsterDefinitionLoader monsterDefinitionLoader;
    private final HashMap<Integer, MonsterDefinition> monsterDefinitionMap = new HashMap<>();

    @Autowired
    public MonsterDefinitionCache(MonsterDefinitionLoader monsterDefinitionLoader) {
        this.monsterDefinitionLoader = monsterDefinitionLoader;
    }

    @PostConstruct
    public void init() throws Exception {
        List<MonsterDefinition> monsterDefinitionList = monsterDefinitionLoader.loadMonsterDefinitions();

        for(MonsterDefinition monsterDefinition : monsterDefinitionList) {
            monsterDefinitionMap.put(monsterDefinition.getId(), monsterDefinition);
        }
    }

    public MonsterDefinition getMonsterDefinition(int monsterId) {
        return monsterDefinitionMap.get(monsterId);
    }
}
