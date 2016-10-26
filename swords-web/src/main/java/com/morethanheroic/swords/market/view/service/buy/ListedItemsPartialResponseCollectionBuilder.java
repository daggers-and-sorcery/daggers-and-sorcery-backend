package com.morethanheroic.swords.market.view.service.buy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.item.view.response.service.IdentifiedItemPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.IdentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.market.view.service.domain.buy.ListItemsToBuyResponseBuilderConfiguration;
import com.morethanheroic.swords.market.view.service.domain.buy.ListedItemsPartialResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListedItemsPartialResponseCollectionBuilder implements PartialResponseCollectionBuilder<ListItemsToBuyResponseBuilderConfiguration> {

    private final IdentifiedItemPartialResponseBuilder identifiedItemPartialResponseBuilder;

    @Override
    public List<PartialResponse> build(final ListItemsToBuyResponseBuilderConfiguration listItemsToBuyResponseBuilderConfiguration) {
        return
            Collections.unmodifiableList(
            listItemsToBuyResponseBuilderConfiguration.getOffers().stream()
            .map((marketOfferInformationEntry) -> ListedItemsPartialResponse.builder()
                .item(identifiedItemPartialResponseBuilder.build(
                       IdentifiedItemPartialResponseBuilderConfiguration.builder()
                            .item(marketOfferInformationEntry.getItem())
                            .build()
                ))
                .amount(marketOfferInformationEntry.getAmount())
                .cheapestPrice(marketOfferInformationEntry.getLowestPrice())
                .build())
            .collect(
                Collectors.toList()
            )
            );
    }
}
