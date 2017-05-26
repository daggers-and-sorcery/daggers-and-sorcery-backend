package com.morethanheroic.swords.journal.view.response.quest.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Gyula_Lakatos
 */
@Getter
@Builder
public class QuestJournalEntryResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final QuestDefinition questDefinition;
}
