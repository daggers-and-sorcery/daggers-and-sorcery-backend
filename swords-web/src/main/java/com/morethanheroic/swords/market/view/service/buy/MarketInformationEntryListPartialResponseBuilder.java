package com.morethanheroic.swords.market.view.service.buy;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.IdentifiedItemPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.IdentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.market.view.service.domain.buy.ListedItemsPartialResponse;
import com.morethanheroic.swords.market.view.service.domain.buy.MarketInformationEntryListPartialResponse;
import com.morethanheroic.swords.market.view.service.domain.buy.MarketInformationEntryListPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarketInformationEntryListPartialResponseBuilder implements PartialResponseBuilder<MarketInformationEntryListPartialResponseBuilderConfiguration> {

    private final IdentifiedItemPartialResponseBuilder identifiedItemPartialResponseBuilder;

    @Override
    public PartialResponse build(MarketInformationEntryListPartialResponseBuilderConfiguration marketInformationEntryListPartialResponseBuilderConfiguration) {
        return MarketInformationEntryListPartialResponse.builder()
                .typeName(marketInformationEntryListPartialResponseBuilderConfiguration.getType().getName())
                .offers(marketInformationEntryListPartialResponseBuilderConfiguration.getOffers().stream()
                        .map(
                                (offer) ->
                                        ListedItemsPartialResponse.builder()
                                                .definition(identifiedItemPartialResponseBuilder.build(
                                                        IdentifiedItemPartialResponseBuilderConfiguration.builder()
                                                                .item(offer.getItem())
                                                                .build()
                                                ))
                                                .amount(offer.getAmount())
                                                .cheapestPrice(offer.getLowestPrice())
                                                .build()
                        )
                        .collect(
                                Collectors.toList()
                        )
                )
                .build();
    }
}
