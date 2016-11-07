package com.morethanheroic.swords.market.view.request.domain;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class BuyItemData {

    @Min(1)
    private int marketEntityId;
}
