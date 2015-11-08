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

    @Autowired
    private MonsterDefinitionLoader monsterDefinitionLoader;

    private final HashMap<Integer, MonsterDefinition> monsterDefinitionMap = new HashMap<>();

    @PostConstruct
    public void init() throws Exception {
        List<MonsterDefinition> monsterDefinitionList = monsterDefinitionLoader.loadMonsterDefinitions();

        for (MonsterDefinition monsterDefinition : monsterDefinitionList) {
            monsterDefinitionMap.put(monsterDefinition.getId(), monsterDefinition);
        }
    }

    public MonsterDefinition getMonsterDefinition(int monsterId) {
        return monsterDefinitionMap.get(monsterId);
    }
}
