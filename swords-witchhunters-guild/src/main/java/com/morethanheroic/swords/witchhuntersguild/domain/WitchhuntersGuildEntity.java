package com.morethanheroic.swords.witchhuntersguild.domain;

import com.morethanheroic.entity.domain.Entity;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.WitchhuntersGuildJobDefinition;
import lombok.Builder;
import lombok.Getter;

/**
 * Contains the user's progress in the witchhunter's guild.
 */
@Getter
@Builder
public class WitchhuntersGuildEntity implements Entity {

    private final int id;
    private final int reputationPoints;
    private final WitchhuntersGuildRank rank;
    private final WitchhuntersGuildJobDefinition job;
}
