package com.morethanheroic.swords.shop.view.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShopDefinitionPartialResponse extends PartialResponse {

    private final int id;
    private final String name;
}
