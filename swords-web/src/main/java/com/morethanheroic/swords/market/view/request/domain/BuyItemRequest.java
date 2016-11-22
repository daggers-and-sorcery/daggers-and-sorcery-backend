package com.morethanheroic.swords.market.view.request.domain;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class BuyItemRequest {

    @Min(1)
    private int marketEntityId;
}
