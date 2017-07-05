package com.morethanheroic.swords.witchhuntersguild.view.guildhall.response.service.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.witchhuntersguild.domain.WitchhuntersGuildEntity;
import com.morethanheroic.swords.witchhuntersguild.view.guildhall.response.service.WitchhuntersGuildGuildHallResponseBuilder;
import lombok.Builder;
import lombok.Getter;

/**
 * Configuration for the {@link WitchhuntersGuildGuildHallResponseBuilder}.
 */
@Getter
@Builder
public class WitchhuntersGuildGuildHallResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final WitchhuntersGuildEntity witchhuntersGuildEntity;
}
