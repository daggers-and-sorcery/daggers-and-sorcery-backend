package com.morethanheroic.swords.journal.domain.entity;

import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MonsterJournalEntity implements JournalEntity {

    private final int id;
    private final MonsterDefinition monsterDefinition;

    @Override
    public int getId() {
        return id;
    }
}
