package com.morethanheroic.swords.skill.scavenging.service.domain;

import com.morethanheroic.swords.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ScavengingPointsPartialResponse extends PartialResponse {

    private final int scavengingPoints;
}
