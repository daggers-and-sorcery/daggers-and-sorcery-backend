package com.morethanheroic.swords.item.view.response.service.domain;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.IdentifiedItemPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.UnidentifiedItemPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.IdentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.ItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.UnidentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.item.view.response.service.domain.response.ItemDefinitionPartialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemPartialResponseBuilder implements PartialResponseBuilder<ItemPartialResponseBuilderConfiguration> {

    private final IdentifiedItemPartialResponseBuilder identifiedItemPartialResponseBuilder;
    private final UnidentifiedItemPartialResponseBuilder unidentifiedItemPartialResponseBuilder;

    @Override
    public ItemDefinitionPartialResponse build(final ItemPartialResponseBuilderConfiguration itemPartialResponseBuilderConfiguration) {
        if (itemPartialResponseBuilderConfiguration.isIdentified()) {
            return identifiedItemPartialResponseBuilder.build(
                    IdentifiedItemPartialResponseBuilderConfiguration.builder()
                            .item(itemPartialResponseBuilderConfiguration.getItem())
                            .build()
            );
        } else {
            return unidentifiedItemPartialResponseBuilder.build(
                    UnidentifiedItemPartialResponseBuilderConfiguration.builder()
                            .item(itemPartialResponseBuilderConfiguration.getItem())
                            .sessionEntity(itemPartialResponseBuilderConfiguration.getSessionEntity())
                            .build()
            );
        }
    }
}
