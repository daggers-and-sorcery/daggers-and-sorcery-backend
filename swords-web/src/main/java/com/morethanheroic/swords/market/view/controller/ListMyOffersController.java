package com.morethanheroic.swords.market.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.market.service.MyOffersListingService;
import com.morethanheroic.swords.market.view.response.service.ListMyOffersResponseBuilder;
import com.morethanheroic.swords.market.view.response.service.domain.ListMyOffersResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ListMyOffersController {

    private final MyOffersListingService myOffersListingService;
    private final ListMyOffersResponseBuilder listMyOffersResponseBuilder;

    @GetMapping("/market/show/listings")
    public Response listMyOffers(final UserEntity userEntity) {
        return listMyOffersResponseBuilder.build(
                ListMyOffersResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .offers(myOffersListingService.showMyListings(userEntity))
                        .build()
        );
    }
}
