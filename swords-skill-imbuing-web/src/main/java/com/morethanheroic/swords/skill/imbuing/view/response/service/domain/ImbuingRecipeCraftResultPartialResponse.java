package com.morethanheroic.swords.skill.imbuing.view.response.service.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ImbuingRecipeCraftResultPartialResponse extends PartialResponse {

    private final boolean successful;
    private final String result;
}
