package com.morethanheroic.swords.inn.service.response.service.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.money.domain.MoneyType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InnServicePartialResponse extends PartialResponse {

    private final String id;
    private final String name;
}
