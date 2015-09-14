package com.morethanheroic.swords.journal.service;

import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.common.response.ResponseFactory;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JournalListResponseBuilder {

    private final JournalManager journalManager;
    private final ResponseFactory responseFactory;

    @Autowired
    public JournalListResponseBuilder(JournalManager journalManager, ResponseFactory responseFactory) {
        this.journalManager = journalManager;
        this.responseFactory = responseFactory;
    }

    public Response build(UserEntity userEntity, JournalType journalType) {
        Response response = responseFactory.newResponse(userEntity);

        response.setData("journal_info", journalManager.getJournalEntryListByType(userEntity, journalType));

        return response;
    }
}
