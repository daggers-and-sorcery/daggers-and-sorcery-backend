package com.morethanheroic.swords.journal.service.item;

import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.journal.domain.entity.ItemJournalEntity;
import com.morethanheroic.swords.journal.repository.dao.JournalDatabaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemJournalEntityTransformer {

    private final ItemDefinitionCache itemDefinitionCache;

    public ItemJournalEntity transform(final JournalDatabaseEntity journalDatabaseEntity) {
        return ItemJournalEntity.builder()
                .id(journalDatabaseEntity.getUserId())
                .itemDefinition(itemDefinitionCache.getDefinition(journalDatabaseEntity.getJournalId()))
                .build();
    }
}
