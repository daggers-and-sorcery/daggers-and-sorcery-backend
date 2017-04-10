package com.morethanheroic.swords.skill.herblore.service.gathering.event;

import com.morethanheroic.swords.event.domain.Event;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Controls what should happen when a gathering event finishes.
 */
@Service
@RequiredArgsConstructor
public class GatheringEvent implements Event {

    private static final int GATHERING_EVENT_ID = 4;
    private static final int KINGS_BLOOD_ID = 185;

    private final InventoryEntityFactory inventoryEntityFactory;
    private final ItemDefinitionCache itemDefinitionCache;

    @Override
    public int getId() {
        return GATHERING_EVENT_ID;
    }

    @Override
    public void processEvent(final UserEntity userEntity) {
        //TODO: Add variation based on herblore level
        inventoryEntityFactory.getEntity(userEntity).addItem(itemDefinitionCache.getDefinition(KINGS_BLOOD_ID), 1);
    }
}
