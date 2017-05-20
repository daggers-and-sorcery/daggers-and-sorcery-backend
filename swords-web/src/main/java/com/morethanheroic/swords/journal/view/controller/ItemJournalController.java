package com.morethanheroic.swords.journal.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.journal.view.response.ItemJournalListResponseBuilder;
import com.morethanheroic.swords.journal.view.response.domain.ItemJournalListResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemJournalController {

    private final ItemJournalListResponseBuilder itemJournalListResponseBuilder;

    @GetMapping("/journal/list/item")
    public Response listJournal(final UserEntity userEntity) {
        return itemJournalListResponseBuilder.build(
                ItemJournalListResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }
}
