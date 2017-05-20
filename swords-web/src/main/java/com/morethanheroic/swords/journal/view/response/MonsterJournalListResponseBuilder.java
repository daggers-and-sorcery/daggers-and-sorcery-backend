package com.morethanheroic.swords.journal.view.response;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.journal.domain.entity.MonsterJournalEntity;
import com.morethanheroic.swords.journal.service.monster.MonsterJournalEntityFactory;
import com.morethanheroic.swords.journal.view.response.domain.JournalListResponseEntry;
import com.morethanheroic.swords.journal.view.response.domain.MonsterJournalListResponseBuilderConfiguration;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MonsterJournalListResponseBuilder implements ResponseBuilder<MonsterJournalListResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final MonsterJournalEntityFactory monsterJournalEntityFactory;

    @Override
    public Response build(final MonsterJournalListResponseBuilderConfiguration monsterJournalListResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(monsterJournalListResponseBuilderConfiguration.getUserEntity());

        response.setData("journalInfo",
                monsterJournalEntityFactory.getEntity(monsterJournalListResponseBuilderConfiguration.getUserEntity()).stream()
                        .map(this::convertMonsterEntity)
                        .collect(Collectors.toList())
        );

        return response;
    }

    private JournalListResponseEntry convertMonsterEntity(final MonsterJournalEntity entity) {
        final MonsterDefinition monsterDefinition = entity.getMonsterDefinition();

        return JournalListResponseEntry.builder()
                .entryId(monsterDefinition.getId())
                .entryName(monsterDefinition.getName())
                .build();
    }
}
