package com.morethanheroic.swords.monster.service.cache;

import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.service.loader.MonsterDefinitionLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MonsterDefinitionCache implements DefinitionCache<Integer, MonsterDefinition> {

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

    /**
     * @deprecated Use {@link #getDefinition(Integer)} instead.
     */
    @Deprecated
    public MonsterDefinition getMonsterDefinition(int monsterId) {
        return monsterDefinitionMap.get(monsterId);
    }

    @Override
    public MonsterDefinition getDefinition(Integer monsterId) {
        return monsterDefinitionMap.get(monsterId);
    }

    @Override
    public int getSize() {
        return monsterDefinitionMap.size();
    }

    @Override
    public List<MonsterDefinition> getDefinitions() {
        return new ArrayList<>(monsterDefinitionMap.values());
    }

    @Override
    public boolean isDefinitionExists(Integer key) {
        return monsterDefinitionMap.containsKey(key);
    }
}
