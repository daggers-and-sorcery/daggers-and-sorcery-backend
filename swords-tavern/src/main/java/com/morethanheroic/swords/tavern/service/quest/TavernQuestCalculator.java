package com.morethanheroic.swords.tavern.service.quest;

import com.morethanheroic.swords.location.domain.Location;
import com.morethanheroic.swords.location.service.LocationCalculator;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import com.morethanheroic.swords.quest.service.entity.QuestEntityFactory;
import com.morethanheroic.swords.quest.service.entity.domain.QuestEntityFactoryContext;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.vampire.service.VampireCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TavernQuestCalculator {

    private static final int SMUGGLERS_HEAVEN_QUEST_ID = 3;

    private final LocationCalculator locationCalculator;
    private final VampireCalculator vampireCalculator;
    private final QuestDefinitionCache questDefinitionCache;
    private final QuestEntityFactory questEntityFactory;

    public boolean shouldShowSmugglersHeavenQuest(final UserEntity userEntity) {
        return vampireCalculator.isVampire(userEntity) &&
                locationCalculator.isAtLocation(userEntity, Location.SEVGARD) &&
                !alreadyStarted(userEntity);
    }

    private boolean alreadyStarted(final UserEntity userEntity) {
        return questEntityFactory.getEntity(
                QuestEntityFactoryContext.builder()
                        .userEntity(userEntity)
                        .questDefinition(questDefinitionCache.getDefinition(SMUGGLERS_HEAVEN_QUEST_ID))
                        .build()
        ).isStarted();
    }
}
