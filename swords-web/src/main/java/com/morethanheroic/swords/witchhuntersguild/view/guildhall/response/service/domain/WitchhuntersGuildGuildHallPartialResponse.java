package com.morethanheroic.swords.witchhuntersguild.view.guildhall.response.service.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WitchhuntersGuildGuildHallPartialResponse extends PartialResponse {

    private final int reputationPoints;
    private final String rank;
}
