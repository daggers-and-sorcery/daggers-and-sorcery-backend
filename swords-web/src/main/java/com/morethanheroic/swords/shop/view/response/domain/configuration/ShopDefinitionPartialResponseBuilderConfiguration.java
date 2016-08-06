package com.morethanheroic.swords.shop.view.response.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShopDefinitionPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final ShopDefinition shopDefinition;
}
