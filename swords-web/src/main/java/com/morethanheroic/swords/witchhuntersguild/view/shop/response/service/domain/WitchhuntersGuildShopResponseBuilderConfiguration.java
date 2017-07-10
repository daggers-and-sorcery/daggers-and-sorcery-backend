package com.morethanheroic.swords.witchhuntersguild.view.shop.response.service.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.witchhuntersguild.view.shop.response.service.WitchhuntersGuildShopResponseBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

/**
 * Configuration for the {@link WitchhuntersGuildShopResponseBuilder}.
 */
@Builder
@Getter
public class WitchhuntersGuildShopResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final Optional<ShopDefinition> shopDefinition;
}
