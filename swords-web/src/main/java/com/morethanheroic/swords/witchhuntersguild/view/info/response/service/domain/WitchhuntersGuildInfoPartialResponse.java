package com.morethanheroic.swords.witchhuntersguild.view.info.response.service.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WitchhuntersGuildInfoPartialResponse extends PartialResponse {

    private final boolean accessible;
    private final boolean questStarted;
}
