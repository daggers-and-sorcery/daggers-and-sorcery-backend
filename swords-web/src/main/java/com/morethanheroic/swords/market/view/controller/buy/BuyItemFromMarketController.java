package com.morethanheroic.swords.market.view.controller.buy;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.market.service.BuyerService;
import com.morethanheroic.swords.market.service.MarketEntityFactory;
import com.morethanheroic.swords.market.view.request.domain.BuyItemData;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BuyItemFromMarketController {

    private final BuyerService buyerService;
    private final MarketEntityFactory marketEntityFactory;
    private final ResponseFactory responseFactory;

    @PostMapping("/market/buy")
    public Response buyItem(final UserEntity userEntity, @RequestBody @Valid final BuyItemData buyItemData) {
        buyerService.buyFromMarket(userEntity, marketEntityFactory.getEntity(buyItemData.getMarketEntityId()));

        return responseFactory.successfulResponse(userEntity);
    }
}
