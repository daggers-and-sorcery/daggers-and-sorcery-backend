package com.morethanheroic.swords.item.view.response.service.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ItemPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final ItemDefinition item;
    private final boolean isIdentified;
    private final SessionEntity sessionEntity;
}