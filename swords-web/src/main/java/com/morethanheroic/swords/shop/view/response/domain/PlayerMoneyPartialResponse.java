package com.morethanheroic.swords.shop.view.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlayerMoneyPartialResponse extends PartialResponse {

    private final int bronze;
    private final int silver;
    private final int gold;
}
