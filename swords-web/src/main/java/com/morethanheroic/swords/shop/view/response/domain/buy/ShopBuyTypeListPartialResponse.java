package com.morethanheroic.swords.shop.view.response.domain.buy;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.shop.view.response.domain.PlayerMoneyPartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ShopBuyTypeListPartialResponse extends PartialResponse {

    private final String typeName;
    private final PlayerMoneyPartialResponse money;
    private final List<ShopBuyItemPartialResponse> items;
}
