package com.morethanheroic.swords.settings.service;

import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.common.response.ResponseFactory;
import com.morethanheroic.swords.journal.repository.dao.JournalDatabaseEntity;
import com.morethanheroic.swords.monster.service.MonsterDefinitionCache;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SpecificMonstersResponseBuilder {

    private final ResponseFactory responseFactory;
    private final MonsterDefinitionCache monsterDefinitionCache;

    @Autowired
    public SpecificMonstersResponseBuilder(ResponseFactory responseFactory, MonsterDefinitionCache monsterDefinitionCache) {
        this.responseFactory = responseFactory;
        this.monsterDefinitionCache = monsterDefinitionCache;
    }

    public Response build(UserEntity userEntity, List<JournalDatabaseEntity> journalMonsters) {
        Response response =  responseFactory.newResponse(userEntity);

        ArrayList<HashMap<String, Object>> monstersResult = new ArrayList<>();
        for(JournalDatabaseEntity journalEntry : journalMonsters) {
            MonsterDefinition monsterDefinition = monsterDefinitionCache.getMonsterDefinition(journalEntry.getJournalId());
            HashMap<String, Object> monsterData = new HashMap<>();

            monsterData.put("id", monsterDefinition.getId());
            monsterData.put("name", monsterDefinition.getName());

            monstersResult.add(monsterData);
        }

        response.setData("monsterList", monstersResult);

        return response;
    }
}
