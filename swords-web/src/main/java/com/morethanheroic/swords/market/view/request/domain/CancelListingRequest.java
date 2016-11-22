package com.morethanheroic.swords.market.view.request.domain;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class CancelListingRequest {

    @Min(1)
    private int marketEntityId;
}
