package com.morethanheroic.swords.journal.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.journal.view.response.MonsterJournalListResponseBuilder;
import com.morethanheroic.swords.journal.view.response.domain.MonsterJournalListResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MonsterJournalController {

    private final MonsterJournalListResponseBuilder monsterJournalListResponseBuilder;

    @GetMapping("/journal/list/monster")
    public Response listJournal(final UserEntity userEntity) {
        return monsterJournalListResponseBuilder.build(
                MonsterJournalListResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }
}
