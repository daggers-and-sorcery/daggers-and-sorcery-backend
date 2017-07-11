package com.morethanheroic.swords.witchhuntersguild.service.shop;

import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.service.definition.cache.ShopDefinitionCache;
import com.morethanheroic.swords.witchhuntersguild.domain.WitchhuntersGuildEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WitchhuntersGuildShopCalculator {

    private static final int NOVICE_SHOP_ID = 2;
    private static final int APPRENTICE_SHOP_ID = 3;
    private static final int ADEPT_SHOP_ID = APPRENTICE_SHOP_ID;

    private final ShopDefinitionCache shopDefinitionCache;

    public Optional<ShopDefinition> calculateShop(final WitchhuntersGuildEntity witchhuntersGuildEntity) {
        switch (witchhuntersGuildEntity.getRank()) {
            case NOVICE:
                return Optional.of(shopDefinitionCache.getDefinition(NOVICE_SHOP_ID));
            case APPRENTICE:
                return Optional.of(shopDefinitionCache.getDefinition(APPRENTICE_SHOP_ID));
            case ADEPT:
                return Optional.of(shopDefinitionCache.getDefinition(ADEPT_SHOP_ID));
            default:
                return Optional.empty();
        }
    }
}
