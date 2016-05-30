package com.morethanheroic.swords.skill.leatherworking.service.response.domain.configuration;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CuringListPartialResponse extends PartialResponse {

    private String item;
    private long timeLeft;
    private long fullTime;
}
