package com.morethanheroic.swords.journal.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller for the quest functionality in the journal.
 */
@RestController
public class QuestJournalController {

    @GetMapping("/journal/list/quest")
    public Response listJournal(final UserEntity userEntity) {

    }
}
