package com.morethanheroic.swords.witchhuntersguild.view.quest.response.service.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.WitchhuntersGuildJobDefinition;
import com.morethanheroic.swords.witchhuntersguild.view.quest.response.service.WitchhuntersGuildQuestResponseBuilder;
import lombok.Builder;
import lombok.Getter;

/**
 * A {@link ResponseBuilderConfiguration} for {@link WitchhuntersGuildQuestResponseBuilder}.
 */
@Getter
@Builder
public class WitchhuntersGuildQuestResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final WitchhuntersGuildJobDefinition jobDefinition;
}
