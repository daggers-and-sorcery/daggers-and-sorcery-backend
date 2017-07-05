package com.morethanheroic.swords.journal.service.monster;

import com.morethanheroic.swords.journal.domain.entity.MonsterJournalEntity;
import com.morethanheroic.swords.journal.repository.dao.JournalDatabaseEntity;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonsterJournalEntityTransformer {

    private final MonsterDefinitionCache monsterDefinitionCache;

    public MonsterJournalEntity transform(final JournalDatabaseEntity journalDatabaseEntity) {
        return MonsterJournalEntity.builder()
                .id(journalDatabaseEntity.getUserId())
                .monsterDefinition(monsterDefinitionCache.getDefinition(journalDatabaseEntity.getJournalId()))
                .build();
    }
}
