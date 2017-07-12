package com.morethanheroic.swords.witchhuntersguild.service.rank;

import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.witchhuntersguild.domain.WitchhuntersGuildRank;
import com.morethanheroic.swords.witchhuntersguild.service.WitchhuntersGuildCalculator;
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
        final int witchhunterReputationPoints = witchhuntersGuildCalculator.calculateReputationPoints(userEntity);

        if (witchhunterReputationPoints < 25) {
            return WitchhuntersGuildRank.NEWCOMER;
        } else if (witchhunterReputationPoints < 50) {
            return WitchhuntersGuildRank.NOVICE;
        } else if (witchhunterReputationPoints < 100) {
            return WitchhuntersGuildRank.APPRENTICE;
        } else {
            return WitchhuntersGuildRank.ADEPT;
        }
    }
}
