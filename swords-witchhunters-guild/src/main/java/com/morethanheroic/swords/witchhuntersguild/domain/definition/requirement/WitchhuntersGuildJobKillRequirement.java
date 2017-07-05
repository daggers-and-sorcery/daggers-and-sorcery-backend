package com.morethanheroic.swords.witchhuntersguild.domain.definition.requirement;

import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WitchhuntersGuildJobKillRequirement implements WitchhuntersGuildJobRequirement {

    private final MonsterDefinition monster;
    private final int amount;
}
