package com.morethanheroic.swords.journal.view.response;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.journal.domain.entity.ItemJournalEntity;
import com.morethanheroic.swords.journal.service.item.ItemJournalEntityFactory;
import com.morethanheroic.swords.journal.view.response.domain.ItemJournalListResponseBuilderConfiguration;
import com.morethanheroic.swords.journal.view.response.domain.JournalListResponseEntry;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ItemJournalListResponseBuilder implements ResponseBuilder<ItemJournalListResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final ItemJournalEntityFactory itemJournalEntityFactory;

    @Override
    public Response build(ItemJournalListResponseBuilderConfiguration itemJournalListResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(itemJournalListResponseBuilderConfiguration.getUserEntity());

        response.setData("journalInfo",
                itemJournalEntityFactory.getEntity(itemJournalListResponseBuilderConfiguration.getUserEntity()).stream()
                        .map(this::convertItemEntity)
                        .collect(Collectors.toList())
        );

        return response;
    }

    //TODO: Create a partial response builder from this!
    private JournalListResponseEntry convertItemEntity(final ItemJournalEntity entity) {
        final ItemDefinition itemDefinition = entity.getItemDefinition();

        return JournalListResponseEntry.builder()
                .entryId(itemDefinition.getId())
                .entryName(itemDefinition.getName())
                .build();
    }
}
