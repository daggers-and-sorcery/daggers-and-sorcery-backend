package com.morethanheroic.swords.zone.service.availability.modifier;

import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import com.morethanheroic.swords.quest.service.entity.QuestEntityFactory;
import com.morethanheroic.swords.quest.service.entity.domain.QuestEntityFactoryContext;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.zone.domain.ExplorationZone;
import com.morethanheroic.swords.zone.service.availability.ZoneAvailabilityModifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TombOfTheCursedZoneAvailabilityModifier implements ZoneAvailabilityModifier {

    private static final int CURSED_HEROES_QUEST_ID = 4;

    private final QuestEntityFactory questEntityFactory;
    private final QuestDefinitionCache questDefinitionCache;

    @Override
    public boolean isZoneAvailable(final UserEntity userEntity) {
        return questEntityFactory.getEntity(
                QuestEntityFactoryContext.builder()
                        .userEntity(userEntity)
                        .questDefinition(questDefinitionCache.getDefinition(CURSED_HEROES_QUEST_ID))
                        .build()
        ).isCompleted();
    }

    @Override
    public ExplorationZone isModifierFor() {
        return ExplorationZone.TOMB_OF_THE_CURSED;
    }
}
