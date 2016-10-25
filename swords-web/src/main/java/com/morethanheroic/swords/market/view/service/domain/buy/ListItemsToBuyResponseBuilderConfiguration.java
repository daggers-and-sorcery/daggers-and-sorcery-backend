package com.morethanheroic.swords.market.view.service.domain.buy;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ListItemsToBuyResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final List<ItemDefinition> items;
}
