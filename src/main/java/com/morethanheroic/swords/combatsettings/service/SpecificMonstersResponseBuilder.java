package com.morethanheroic.swords.combatsettings.service;

import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.common.response.ResponseFactory;
import com.morethanheroic.swords.journal.repository.dao.JournalDatabaseEntity;
import com.morethanheroic.swords.monster.service.MonsterDefinitionManager;
import com.morethanheroic.swords.monster.service.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SpecificMonstersResponseBuilder {

    private final ResponseFactory responseFactory;
    private final MonsterDefinitionManager monsterDefinitionManager;

    @Autowired
    public SpecificMonstersResponseBuilder(ResponseFactory responseFactory, MonsterDefinitionManager monsterDefinitionManager) {
        this.responseFactory = responseFactory;
        this.monsterDefinitionManager = monsterDefinitionManager;
    }

    public Response build(UserEntity userEntity, List<JournalDatabaseEntity> journalMonsters) {
        Response response =  responseFactory.newResponse(userEntity);

        ArrayList<HashMap<String, Object>> monstersResult = new ArrayList<>();
        for(JournalDatabaseEntity journalEntry : journalMonsters) {
            MonsterDefinition monsterDefinition = monsterDefinitionManager.getMonsterDefinition(journalEntry.getJournalId());
            HashMap<String, Object> monsterData = new HashMap<>();

            monsterData.put("id", monsterDefinition.getId());
            monsterData.put("name", monsterDefinition.getName());

            monstersResult.add(monsterData);
        }

        response.setData("monsterList", monstersResult);

        return response;
    }
}
