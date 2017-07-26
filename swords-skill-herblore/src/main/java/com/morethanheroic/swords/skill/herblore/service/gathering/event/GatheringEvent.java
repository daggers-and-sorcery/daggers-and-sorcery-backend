package com.morethanheroic.swords.skill.herblore.service.gathering.event;

import com.morethanheroic.swords.event.domain.Event;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Controls what should happen when a gathering event finishes.
 */
@Service
@RequiredArgsConstructor
public class GatheringEvent implements Event {

    private static final int GATHERING_EVENT_ID = 4;
    private static final int KINGS_BLOOD_ID = 185;
    private static final int SILVERLEAF_ID = 202;

    private final InventoryEntityFactory inventoryEntityFactory;
    private final ItemDefinitionCache itemDefinitionCache;
    private final SkillEntityFactory skillEntityFactory;
    private final Random random;

    @Override
    public int getId() {
        return GATHERING_EVENT_ID;
    }

    @Override
    public void processEvent(final UserEntity userEntity) {
        final SkillEntity skillEntity = skillEntityFactory.getEntity(userEntity);
        boolean herbAwarded = false;

        inventoryEntityFactory.getEntity(userEntity).addItem(itemDefinitionCache.getDefinition(KINGS_BLOOD_ID), 1);

        if (skillEntity.getLevel(SkillType.HERBLORE) > 5) {
            if (random.nextInt(100) <= 5) {
                herbAwarded = true;
                inventoryEntityFactory.getEntity(userEntity).addItem(itemDefinitionCache.getDefinition(SILVERLEAF_ID), 1);
            }
        }

        if (!herbAwarded) {
            inventoryEntityFactory.getEntity(userEntity).addItem(itemDefinitionCache.getDefinition(KINGS_BLOOD_ID), 1);
        }
    }
}
