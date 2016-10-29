package com.morethanheroic.swords.market.view.controller.buy;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.market.service.MarketService;
import com.morethanheroic.swords.market.service.sorter.MarketOfferInformationTypeSorter;
import com.morethanheroic.swords.market.view.service.buy.ListItemsToBuyResponseBuilder;
import com.morethanheroic.swords.market.view.service.domain.buy.ListItemsToBuyResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ListItemsToBuyToMarketController {

    private final MarketService marketService;
    private final ListItemsToBuyResponseBuilder listItemsToBuyResponseBuilder;
    private final MarketOfferInformationTypeSorter marketOfferInformationTypeSorter;

    @GetMapping("/market/show/buy/list")
    public Response listItemsToBuy(final UserEntity userEntity) {
        return listItemsToBuyResponseBuilder.build(
                ListItemsToBuyResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .offers(marketOfferInformationTypeSorter.sortByType(marketService.getMarketOfferInformation()))
                        .build()
        );
    }
}
