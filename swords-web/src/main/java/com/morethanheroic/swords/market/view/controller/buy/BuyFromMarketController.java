package com.morethanheroic.swords.market.view.controller.buy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.user.domain.UserEntity;

@RestController
public class BuyFromMarketController {

    @GetMapping("/market/show/buy/list")
    public Response listItemsToBuy() {
        return null;
    }

    @GetMapping("/market/show/buy/{target}")
    public Response showItemToBuy(final UserEntity userEntity, final int target) {
        return null;
    }
}
