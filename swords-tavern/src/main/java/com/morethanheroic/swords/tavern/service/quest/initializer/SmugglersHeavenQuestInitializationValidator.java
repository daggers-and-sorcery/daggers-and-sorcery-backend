package com.morethanheroic.swords.tavern.service.quest.initializer;

import com.morethanheroic.swords.location.domain.Location;
import com.morethanheroic.swords.location.service.LocationCalculator;
import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import com.morethanheroic.swords.quest.service.initialize.validator.QuestInitializationValidator;
import com.morethanheroic.swords.quest.service.initialize.validator.domain.ValidationContext;
import com.morethanheroic.swords.vampire.service.VampireCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmugglersHeavenQuestInitializationValidator implements QuestInitializationValidator {

    private static final int SMUGGLERS_HEAVEN_QUEST_ID = 3;

    private final QuestDefinitionCache questDefinitionCache;
    private final VampireCalculator vampireCalculator;
    private final LocationCalculator locationCalculator;

    @Override
    public boolean isValidStart(ValidationContext validationContext) {
        return vampireCalculator.isVampire(validationContext.getUserEntity())
                && locationCalculator.isAtLocation(validationContext.getUserEntity(), Location.SEVGARD);
    }

    @Override
    public QuestDefinition supportedQuest() {
        return questDefinitionCache.getDefinition(SMUGGLERS_HEAVEN_QUEST_ID);
    }
}
