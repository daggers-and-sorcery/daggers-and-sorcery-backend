package com.morethanheroic.swords.witchhuntersguild.service.quest.initialize.validator;

import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import com.morethanheroic.swords.quest.service.initialize.validator.QuestInitializationValidator;
import com.morethanheroic.swords.quest.service.initialize.validator.domain.ValidationContext;
import com.morethanheroic.swords.witchhuntersguild.service.WitchhuntersGuildCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CuringTheThirstyQuestInitializationValidator implements QuestInitializationValidator {

    private static final int WITCHUNTER_GUILD_UNLOCKING_QUEST_ID = 1;

    private final QuestDefinitionCache questDefinitionCache;
    private final WitchhuntersGuildCalculator witchhuntersGuildCalculator;

    @Override
    public boolean isValidStart(final ValidationContext validationContext) {
        return witchhuntersGuildCalculator.isWitchhuntersGuildUnlocked(validationContext.getUserEntity());
    }

    @Override
    public QuestDefinition supportedQuest() {
        return questDefinitionCache.getDefinition(WITCHUNTER_GUILD_UNLOCKING_QUEST_ID);
    }
}
