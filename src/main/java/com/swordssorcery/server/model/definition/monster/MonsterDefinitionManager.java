package com.swordssorcery.server.model.definition.monster;

import com.swordssorcery.server.loader.definition.XMLDefinitionLoader;
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
        List<MonsterDefinition> monsterDefinitionList = xmlDefinitionLoader.loadDefinitions(MonsterDefinitionList.class, "classpath:data/monsterlist.xml");

        for (MonsterDefinition monsterDefinition : monsterDefinitionList) {
            monsterDefinitionMap.put(monsterDefinition.getId(), monsterDefinition);
        }
    }

    public MonsterDefinition getMonsterDefinition(int monsterId) {
        return monsterDefinitionMap.get(monsterId);
    }
}
