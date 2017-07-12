package com.morethanheroic.swords.witchhuntersguild.view.shop.response.service.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WitchhuntersGuildShopPartialResponse extends PartialResponse {

    private final int shopId;
}
