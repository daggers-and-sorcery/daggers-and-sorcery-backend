package com.morethanheroic.swords.market.view.response.service.buy;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.item.view.response.service.IdentifiedItemPartialResponseBuilder;
import com.morethanheroic.swords.market.view.response.service.domain.buy.ListItemsToBuyResponseBuilderConfiguration;
import com.morethanheroic.swords.market.view.response.service.domain.buy.MarketInformationEntryListPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListedItemsPartialResponseCollectionBuilder implements PartialResponseCollectionBuilder<ListItemsToBuyResponseBuilderConfiguration> {

    private final IdentifiedItemPartialResponseBuilder identifiedItemPartialResponseBuilder;
    private final MarketInformationEntryListPartialResponseBuilder marketInformationEntryListPartialResponseBuilder;

    @Override
    public List<PartialResponse> build(final ListItemsToBuyResponseBuilderConfiguration listItemsToBuyResponseBuilderConfiguration) {
        return
                Collections.unmodifiableList(
                        listItemsToBuyResponseBuilderConfiguration.getOffers().entrySet().stream()
                                .map(
                                        (marketOfferInformationEntry) -> marketInformationEntryListPartialResponseBuilder.build(
                                                MarketInformationEntryListPartialResponseBuilderConfiguration.builder()
                                                        .type(marketOfferInformationEntry.getKey())
                                                        .offers(marketOfferInformationEntry.getValue())
                                                        .build()
                                        )
                                )
                                .collect(
                                        Collectors.toList()
                                )
                );
    }
}
