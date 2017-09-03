package com.morethanheroic.swords.view.response.service.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InnServicePartialResponse extends PartialResponse {

    private final String id;
    private final String name;
}
