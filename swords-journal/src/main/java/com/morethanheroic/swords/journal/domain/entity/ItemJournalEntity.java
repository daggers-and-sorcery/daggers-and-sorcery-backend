package com.morethanheroic.swords.journal.domain.entity;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import lombok.Builder;
import lombok.Getter;

/**
 * Contains the data of an item entity.
 */
@Getter
@Builder
public class ItemJournalEntity implements JournalEntity {

    private final int id;
    private final ItemDefinition itemDefinition;

    @Override
    public int getId() {
        return id;
    }
}
