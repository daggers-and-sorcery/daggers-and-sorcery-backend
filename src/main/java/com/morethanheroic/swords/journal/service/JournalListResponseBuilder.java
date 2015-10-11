package com.morethanheroic.swords.journal.service;

import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.common.response.ResponseFactory;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.repository.dao.JournalDatabaseEntity;
import com.morethanheroic.swords.journal.view.response.JournalListResponseEntry;
import com.morethanheroic.swords.monster.service.MonsterDefinitionCache;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JournalListResponseBuilder {

    private final JournalManager journalManager;
    private final ResponseFactory responseFactory;
    private final ItemDefinitionManager itemDefinitionManager;
    private final MonsterDefinitionCache monsterDefinitionCache;

    @Autowired
    public JournalListResponseBuilder(JournalManager journalManager, ResponseFactory responseFactory, ItemDefinitionManager itemDefinitionManager, MonsterDefinitionCache monsterDefinitionCache) {
        this.journalManager = journalManager;
        this.responseFactory = responseFactory;
        this.itemDefinitionManager = itemDefinitionManager;
        this.monsterDefinitionCache = monsterDefinitionCache;
    }

    public Response build(UserEntity userEntity, JournalType journalType) {
        Response response = responseFactory.newResponse(userEntity);

        response.setData("journal_info", transformDatabaseEntityList(journalManager.getJournalEntryListByType(userEntity, journalType), journalType));

        return response;
    }

    private List<JournalListResponseEntry> transformDatabaseEntityList(List<JournalDatabaseEntity> list, JournalType journalType) {
        List<JournalListResponseEntry> result = new ArrayList<>();

        for(JournalDatabaseEntity entity : list) {
            if (journalType == JournalType.ITEM) {
                result.add(convertItemEntity(entity));
            } else if (journalType == JournalType.MONSTER) {
                result.add(convertMonsterEntity(entity));
            } else {
                throw new IllegalArgumentException("Invalid journal type!");
            }
        }

        return result;
    }

    private JournalListResponseEntry convertItemEntity(JournalDatabaseEntity entity) {
        ItemDefinition itemDefinition = itemDefinitionManager.getItemDefinition(entity.getJournalId());

        return new JournalListResponseEntry(itemDefinition.getId(), itemDefinition.getName());
    }

    private JournalListResponseEntry convertMonsterEntity(JournalDatabaseEntity entity) {
        MonsterDefinition monsterDefinition = monsterDefinitionCache.getMonsterDefinition(entity.getJournalId());

        return new JournalListResponseEntry(monsterDefinition.getId(), monsterDefinition.getName());
    }
}
