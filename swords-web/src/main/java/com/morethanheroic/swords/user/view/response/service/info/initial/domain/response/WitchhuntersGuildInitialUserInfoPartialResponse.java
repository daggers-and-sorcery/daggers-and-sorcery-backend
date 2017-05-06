package com.morethanheroic.swords.user.view.response.service.info.initial.domain.response;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WitchhuntersGuildInitialUserInfoPartialResponse extends PartialResponse {

    private final boolean witchhuntersGuildUnlocked;
}
