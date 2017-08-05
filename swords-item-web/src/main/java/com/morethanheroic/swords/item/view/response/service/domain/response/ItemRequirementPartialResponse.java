package com.morethanheroic.swords.item.view.response.service.domain.response;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ItemRequirementPartialResponse extends PartialResponse {

    private final String attribute;
    private final int value;
}
