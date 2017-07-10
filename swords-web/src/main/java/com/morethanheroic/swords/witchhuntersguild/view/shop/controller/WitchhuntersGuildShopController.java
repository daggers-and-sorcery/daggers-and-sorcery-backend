package com.morethanheroic.swords.witchhuntersguild.view.shop.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.witchhuntersguild.domain.WitchhuntersGuildEntity;
import com.morethanheroic.swords.witchhuntersguild.service.WitchhuntersGuildEntityFactory;
import com.morethanheroic.swords.witchhuntersguild.service.shop.WitchhuntersGuildShopCalculator;
import com.morethanheroic.swords.witchhuntersguild.view.shop.response.service.WitchhuntersGuildShopResponseBuilder;
import com.morethanheroic.swords.witchhuntersguild.view.shop.response.service.domain.WitchhuntersGuildShopResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the Shop function of the Witchhunter's guild.
 */
@RestController
@RequiredArgsConstructor
public class WitchhuntersGuildShopController {

    private final WitchhuntersGuildEntityFactory witchhuntersGuildEntityFactory;
    private final WitchhuntersGuildShopResponseBuilder witchhuntersGuildShopResponseBuilder;
    private final WitchhuntersGuildShopCalculator witchhuntersGuildShopCalculator;

    @GetMapping("/witchhunters-guild/shop")
    public Response showShop(final UserEntity userEntity) {
        final WitchhuntersGuildEntity witchhuntersGuildEntity = witchhuntersGuildEntityFactory.getEntity(userEntity);

        return witchhuntersGuildShopResponseBuilder.build(
                WitchhuntersGuildShopResponseBuilderConfiguration.builder()
                        .shopDefinition(witchhuntersGuildShopCalculator.calculateShop(witchhuntersGuildEntity))
                        .build()
        );
    }
}
