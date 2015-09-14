package com.morethanheroic.swords.journal.view.controller;

import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.common.response.ResponseFactory;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.service.JournalManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JournalController {

    private final JournalManager journalManager;
    private final ResponseFactory responseFactory;

    @Autowired
    public JournalController(JournalManager journalManager, ResponseFactory responseFactory) {
        this.journalManager = journalManager;
        this.responseFactory = responseFactory;
    }

    @RequestMapping(value = "/journal/list/{journal_type}", method = RequestMethod.GET)
    public Response listJournal(UserEntity userEntity, @PathVariable("journal_type") JournalType journalType) {
        Response response = responseFactory.newResponse(userEntity);

        response.setData("journal_info", journalManager.getJournalEntryListByType(userEntity, journalType));

        return response;
    }
}
