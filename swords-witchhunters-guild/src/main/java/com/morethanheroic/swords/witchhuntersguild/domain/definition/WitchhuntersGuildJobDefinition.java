package com.morethanheroic.swords.witchhuntersguild.domain.definition;

import com.morethanheroic.swords.witchhuntersguild.domain.definition.requirement.WitchhuntersGuildJobRequirement;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Contains the data of a quest in the witchunter's guild.
 */
@Getter
@Builder
public class WitchhuntersGuildJobDefinition {

    private final int id;
    private final String name;
    private final String description;
    private final List<WitchhuntersGuildJobRequirement> requirements;
}
