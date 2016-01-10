package com.morethanheroic.swords.journal.service;

import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.repository.dao.JournalDatabaseEntity;
import com.morethanheroic.swords.journal.view.response.JournalListResponseEntry;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
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
    private final ItemDefinitionCache itemDefinitionCache;
    private final MonsterDefinitionCache monsterDefinitionCache;

    @Autowired
    public JournalListResponseBuilder(JournalManager journalManager, ResponseFactory responseFactory, ItemDefinitionCache itemDefinitionCache, MonsterDefinitionCache monsterDefinitionCache) {
        this.journalManager = journalManager;
        this.responseFactory = responseFactory;
        this.itemDefinitionCache = itemDefinitionCache;
        this.monsterDefinitionCache = monsterDefinitionCache;
    }

    public CharacterRefreshResponse build(UserEntity userEntity, JournalType journalType) {
        CharacterRefreshResponse response = responseFactory.newResponse(userEntity);

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
        ItemDefinition itemDefinition = itemDefinitionCache.getDefinition(entity.getJournalId());

        return new JournalListResponseEntry(itemDefinition.getId(), itemDefinition.getName());
    }

    private JournalListResponseEntry convertMonsterEntity(JournalDatabaseEntity entity) {
        MonsterDefinition monsterDefinition = monsterDefinitionCache.getMonsterDefinition(entity.getJournalId());

        return new JournalListResponseEntry(monsterDefinition.getId(), monsterDefinition.getName());
    }
}
