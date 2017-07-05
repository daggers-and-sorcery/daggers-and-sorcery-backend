package com.morethanheroic.swords.witchhuntersguild.service;

import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.witchhuntersguild.domain.WitchhuntersGuildRank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Calculate the user's {@link WitchhuntersGuildRank}.
 */
@Service
@RequiredArgsConstructor
public class WitchhuntersGuildRankCalculator {

    private final WitchhuntersGuildCalculator witchhuntersGuildCalculator;

    public WitchhuntersGuildRank calculate(final UserEntity userEntity) {
        final int witchhunterReputationpoints = witchhuntersGuildCalculator.calculateReputationPoints(userEntity);

        if (witchhunterReputationpoints < 25) {
            return WitchhuntersGuildRank.NEWCOMER;
        } else if (witchhunterReputationpoints >= 25 && witchhunterReputationpoints < 50) {
            return WitchhuntersGuildRank.NOVICE;
        } else if (witchhunterReputationpoints >= 50 && witchhunterReputationpoints < 100) {
            return WitchhuntersGuildRank.APPRENTICE;
        } else {
            return WitchhuntersGuildRank.ADEPT;
        }
    }
}
