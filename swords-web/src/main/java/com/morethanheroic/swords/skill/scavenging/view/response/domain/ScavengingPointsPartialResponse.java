package com.morethanheroic.swords.skill.scavenging.view.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ScavengingPointsPartialResponse extends PartialResponse {

    private final int scavengingPoints;
}
