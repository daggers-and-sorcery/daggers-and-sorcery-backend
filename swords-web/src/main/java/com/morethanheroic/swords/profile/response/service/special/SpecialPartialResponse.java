package com.morethanheroic.swords.profile.response.service.special;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SpecialPartialResponse extends PartialResponse {

    private final boolean isVampire;
}
