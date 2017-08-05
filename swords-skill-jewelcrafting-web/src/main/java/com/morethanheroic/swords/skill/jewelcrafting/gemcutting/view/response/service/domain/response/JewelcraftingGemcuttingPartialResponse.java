package com.morethanheroic.swords.skill.jewelcrafting.gemcutting.view.response.service.domain.response;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.skill.jewelcrafting.service.gemcutting.domain.GemcuttingResult;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JewelcraftingGemcuttingPartialResponse extends PartialResponse {

    private final GemcuttingResult gemcuttingResult;
}
