package com.morethanheroic.swords.witchhuntersguild.service.shop;

import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.service.availability.ShopAvailabilityEvaluator;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.witchhuntersguild.domain.WitchhuntersGuildRank;
import com.morethanheroic.swords.witchhuntersguild.service.WitchhuntersGuildEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Disable the shop for people that doesn't have sufficient witchhunter's guild level.
 */
@Service
@RequiredArgsConstructor
public class WitchhuntersGuildShopAvailabilityEvaluator implements ShopAvailabilityEvaluator {

    private static final int NOVICE_SHOP_ID = 2;
    private static final int APPRENTICE_SHOP_ID = 3;

    private final WitchhuntersGuildEntityFactory witchhuntersGuildEntityFactory;

    @Override
    public boolean isAvailable(final UserEntity userEntity, final ShopDefinition shopDefinition) {
        final WitchhuntersGuildRank witchhuntersGuildRank = witchhuntersGuildEntityFactory.getEntity(userEntity).getRank();

        if (shopDefinition.getId() == NOVICE_SHOP_ID) {
            return witchhuntersGuildRank.equals(WitchhuntersGuildRank.NOVICE);
        } else if (shopDefinition.getId() == APPRENTICE_SHOP_ID) {
            return witchhuntersGuildRank.equals(WitchhuntersGuildRank.APPRENTICE) || witchhuntersGuildRank.equals(WitchhuntersGuildRank.ADEPT);
        }

        return true;
    }
}