package com.morethanheroic.swords.shop.view.response.domain.buy;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ShopBuyTypeListPartialResponse extends PartialResponse {

    private final String typeName;
    private final List<ShopBuyItemPartialResponse> items;
}
