package com.swordssorcery.server.game.monster;

import com.swordssorcery.server.loader.definition.XMLDefinitionLoader;
import com.swordssorcery.server.model.definition.monster.MonsterDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Service
public class MonsterDefinitionManager {

    @Autowired
    private XMLDefinitionLoader xmlDefinitionLoader;

    private HashMap<Integer, MonsterDefinition> monsterDefinitionMap = new HashMap<>();

    @PostConstruct
    public void init() throws Exception {
        List<MonsterDefinition> monsterDefinitionList = xmlDefinitionLoader.loadDefinitions(MonsterDefinition.class, "classpath:data/monster/definition/", "classpath:data/monster/schema.xsd");

        for (MonsterDefinition monsterDefinition : monsterDefinitionList) {
            monsterDefinitionMap.put(monsterDefinition.getId(), monsterDefinition);
        }
    }

    public MonsterDefinition getMonsterDefinition(int monsterId) {
        return monsterDefinitionMap.get(monsterId);
    }
}
