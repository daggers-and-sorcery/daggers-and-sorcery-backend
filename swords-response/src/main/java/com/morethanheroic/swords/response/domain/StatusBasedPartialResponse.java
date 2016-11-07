package com.morethanheroic.swords.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StatusBasedPartialResponse extends PartialResponse {

    private final boolean successful;
}
