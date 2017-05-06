package com.morethanheroic.swords.witchhuntersguild.service;

import com.morethanheroic.entity.service.factory.EntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.witchhuntersguild.domain.WitchhuntersGuildEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WitchhuntersGuildEntityFactory implements EntityFactory<WitchhuntersGuildEntity, UserEntity> {

    private final WitchhuntersGuildCalculator witchhuntersGuildCalculator;
    private final WitchhuntersGuildRankCalculator witchhuntersGuildRankCalculator;

    @Override
    public WitchhuntersGuildEntity getEntity(final UserEntity userEntity) {
        return WitchhuntersGuildEntity.builder()
                .id(userEntity.getId())
                .reputationPoints(witchhuntersGuildCalculator.calculateReputationPoints(userEntity))
                .rank(witchhuntersGuildRankCalculator.calculate(userEntity))
                //.job() TODO!!!
                .build();
    }
}
