package com.morethanheroic.swords.view.response.order.domain;

import com.morethanheroic.response.domain.PartialResponse;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InnServiceOrderResultPartialResponse extends PartialResponse {

    private final boolean successful;
}