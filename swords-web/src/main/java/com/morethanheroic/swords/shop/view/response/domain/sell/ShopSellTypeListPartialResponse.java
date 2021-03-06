package com.morethanheroic.swords.shop.view.response.domain.sell;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.shop.view.response.domain.PlayerMoneyPartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ShopSellTypeListPartialResponse extends PartialResponse {

    private final String typeName;
    private final PlayerMoneyPartialResponse money;
    private final List<ShopSellItemPartialResponse> items;
}
