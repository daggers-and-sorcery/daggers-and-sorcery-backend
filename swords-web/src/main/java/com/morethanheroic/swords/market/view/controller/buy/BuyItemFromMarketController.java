package com.morethanheroic.swords.market.view.controller.buy;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.market.domain.BuyingResult;
import com.morethanheroic.swords.market.service.BuyingService;
import com.morethanheroic.swords.market.view.request.domain.BuyItemRequest;
import com.morethanheroic.swords.market.view.response.service.buy.BuyItemResponseBuilder;
import com.morethanheroic.swords.market.view.response.service.domain.buy.BuyItemResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BuyItemFromMarketController {

    private final BuyingService buyerService;
    private final BuyItemResponseBuilder buyItemResponseBuilder;

    @PostMapping("/market/buy")
    public Response buyItem(final UserEntity userEntity, @RequestBody @Valid final BuyItemRequest buyItemRequest) {
        final BuyingResult buyingResult = buyerService.buyFromMarket(userEntity, buyItemRequest.getMarketEntityId());

        return buyItemResponseBuilder.build(
                BuyItemResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .buyingResult(buyingResult)
                        .build()
        );
    }
}
