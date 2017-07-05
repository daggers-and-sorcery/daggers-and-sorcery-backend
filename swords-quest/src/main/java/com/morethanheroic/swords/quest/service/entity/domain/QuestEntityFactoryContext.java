package com.morethanheroic.swords.quest.service.entity.domain;

import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestEntityFactoryContext {

    private final UserEntity userEntity;
    private final QuestDefinition questDefinition;
}
