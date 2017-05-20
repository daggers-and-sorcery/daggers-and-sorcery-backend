package com.morethanheroic.swords.journal.view.response;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.journal.domain.JournalType;
import com.morethanheroic.swords.journal.domain.entity.ItemJournalEntity;
import com.morethanheroic.swords.journal.domain.entity.MonsterJournalEntity;
import com.morethanheroic.swords.journal.service.item.ItemJournalEntityFactory;
import com.morethanheroic.swords.journal.service.monster.MonsterJournalEntityFactory;
import com.morethanheroic.swords.journal.view.response.domain.JournalListResponseEntry;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JournalListResponseBuilder {

    private final ResponseFactory responseFactory;
    private final ItemJournalEntityFactory itemJournalEntityFactory;
    private final MonsterJournalEntityFactory monsterJournalEntityFactory;

    public Response build(final UserEntity userEntity, final JournalType journalType) {
        final Response response = responseFactory.newResponse(userEntity);

        if (journalType == JournalType.ITEM) {
            response.setData("journalInfo",
                    itemJournalEntityFactory.getEntity(userEntity).stream()
                            .map(this::convertItemEntity)
                            .collect(Collectors.toList())
            );
        } else if (journalType == JournalType.MONSTER) {
            response.setData("journalInfo",
                    monsterJournalEntityFactory.getEntity(userEntity).stream()
                            .map(this::convertMonsterEntity)
                            .collect(Collectors.toList())
            );
        } else if (journalType == JournalType.QUEST) {
            //TODO!
        } else {
            throw new IllegalArgumentException("Unknown journal type: " + journalType + "!");
        }

        return response;
    }

    private JournalListResponseEntry convertItemEntity(final ItemJournalEntity entity) {
        final ItemDefinition itemDefinition = entity.getItemDefinition();

        return JournalListResponseEntry.builder()
                .entryId(itemDefinition.getId())
                .entryName(itemDefinition.getName())
                .build();
    }

    private JournalListResponseEntry convertMonsterEntity(final MonsterJournalEntity entity) {
        final MonsterDefinition monsterDefinition = entity.getMonsterDefinition();

        return JournalListResponseEntry.builder()
                .entryId(monsterDefinition.getId())
                .entryName(monsterDefinition.getName())
                .build();
    }
}
