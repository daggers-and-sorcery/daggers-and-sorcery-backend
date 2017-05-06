package com.morethanheroic.swords.witchhuntersguild.service;

import com.morethanheroic.swords.metadata.service.MetadataEntityFactory;
import com.morethanheroic.swords.witchhuntersguild.service.definition.cache.WitchhuntersGuildJobDefinitionCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Provides a way to manipulate jobs in the witchunter's guild.
 */
@Service
@RequiredArgsConstructor
public class WitchhunterGuildJobManipulator {

    private final MetadataEntityFactory metadataEntityFactory;
    private final WitchhuntersGuildJobDefinitionCache witchhuntersGuildJobDefinitionCache;

    /**
    public boolean canFinishJob() {

    }

    public void finishJob() {

    }

    public Optional<WitchhuntersGuildJobDefinition> getActualJob() {

    }
     **/
}
