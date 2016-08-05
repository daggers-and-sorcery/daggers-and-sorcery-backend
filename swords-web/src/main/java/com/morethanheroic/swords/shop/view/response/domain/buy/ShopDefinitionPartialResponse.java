package com.morethanheroic.swords.shop.view.response.domain.buy;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShopDefinitionPartialResponse extends PartialResponse {

    private final String name;
}
