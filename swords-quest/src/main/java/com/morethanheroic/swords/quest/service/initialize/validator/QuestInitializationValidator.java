package com.morethanheroic.swords.quest.service.initialize.validator;

import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.quest.service.initialize.validator.domain.ValidationContext;

/**
 * Validate if the player should be able to start a quest.
 */
public interface QuestInitializationValidator {

    boolean isValidStart(ValidationContext validationContext);

    QuestDefinition supportedQuest();
}
