package com.morethanheroic.swords.market.view.response.service.sell;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.IdentifiedItemPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.IdentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.market.view.response.service.domain.sell.SellItemToMaketItemPartialResponse;
import com.morethanheroic.swords.market.view.response.service.domain.sell.SellItemToMarketItemTypeListPartialResponse;
import com.morethanheroic.swords.market.view.response.service.domain.sell.SellItemToMarketItemTypeListPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarketEntityTypeInformationPartialResponseBuilder implements PartialResponseBuilder<SellItemToMarketItemTypeListPartialResponseBuilderConfiguration> {

    private final IdentifiedItemPartialResponseBuilder identifiedItemPartialResponseBuilder;

    @Override
    public PartialResponse build(final SellItemToMarketItemTypeListPartialResponseBuilderConfiguration sellItemToMarketItemTypeListPartialResponseBuilderConfiguration) {
        return SellItemToMarketItemTypeListPartialResponse.builder()
                .typeName(sellItemToMarketItemTypeListPartialResponseBuilderConfiguration.getItemType())
                .items(
                        sellItemToMarketItemTypeListPartialResponseBuilderConfiguration.getItems()
                                .stream()
                                .filter(inventoryItem -> inventoryItem.getItem().isTradeable())
                                .map(
                                        (inventoryItem) -> SellItemToMaketItemPartialResponse.builder()
                                                .amount(inventoryItem.getAmount())
                                                .definition(
                                                        identifiedItemPartialResponseBuilder.build(
                                                                IdentifiedItemPartialResponseBuilderConfiguration.builder()
                                                                        .item(inventoryItem.getItem())
                                                                        .build()
                                                        )
                                                )
                                                .build()
                                )
                                .collect(
                                        Collectors.toList()
                                )
                )
                .build();
    }
}
