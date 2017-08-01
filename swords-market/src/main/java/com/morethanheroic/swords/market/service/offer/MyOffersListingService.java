package com.morethanheroic.swords.market.service.offer;

import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.market.domain.MarketEntity;
import com.morethanheroic.swords.market.repository.repository.MarketMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyOffersListingService {

    private final MarketMapper marketMapper;
    private final UserEntityFactory userEntityFactory;
    private final ItemDefinitionCache itemDefinitionCache;

    public List<MarketEntity> showMyListings(final UserEntity userEntity) {
        return marketMapper.getMyOffers(userEntity.getId()).stream()
                .map((entity) -> MarketEntity.builder()
                        .id(entity.getId())
                        .seller(userEntityFactory.getEntity(entity.getSeller()))
                        .amount(entity.getAmount())
                        .price(entity.getPrice())
                        .item(itemDefinitionCache.getDefinition(entity.getItem()))
                        .build()
                )
                .collect(
                        Collectors.toList()
                );
    }
}
