package com.morethanheroic.swords.witchhuntersguild.service;

import com.morethanheroic.entity.service.factory.EntityFactory;
import com.morethanheroic.swords.metadata.service.MetadataEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.witchhuntersguild.domain.WitchhuntersGuildEntity;
import com.morethanheroic.swords.witchhuntersguild.service.definition.cache.WitchhuntersGuildJobDefinitionCache;
import com.morethanheroic.swords.witchhuntersguild.service.rank.WitchhuntersGuildRankCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WitchhuntersGuildEntityFactory implements EntityFactory<WitchhuntersGuildEntity, UserEntity> {

    private static final String WITCHHUNTERS_GUILD_JOB_ID = "WITCHHUNTERS_GUILD_JOB";

    private final WitchhuntersGuildRankCalculator witchhuntersGuildRankCalculator;
    private final WitchhuntersGuildJobDefinitionCache witchhuntersGuildJobDefinitionCache;
    private final MetadataEntityFactory metadataEntityFactory;
    private final WitchhuntersGuildCalculator witchhuntersGuildCalculator;

    @Override
    public WitchhuntersGuildEntity getEntity(final UserEntity userEntity) {
        final int jobId = metadataEntityFactory.getNumericEntity(userEntity, WITCHHUNTERS_GUILD_JOB_ID).getValue();

        return WitchhuntersGuildEntity.builder()
                .id(userEntity.getId())
                .reputationPoints(witchhuntersGuildCalculator.calculateReputationPoints(userEntity))
                .rank(witchhuntersGuildRankCalculator.calculate(userEntity))
                .job(witchhuntersGuildJobDefinitionCache.getDefinition(jobId))
                .build();
    }
}
