package com.morethanheroic.swords.journal.view.response.quest;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.journal.view.response.quest.domain.QuestJournalEntryResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestJournalEntryResponseBuilder implements ResponseBuilder<QuestJournalEntryResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final QuestJournalEntryPartialResponseBuilder questJournalEntryPartialResponseBuilder;

    @Override
    public Response build(final QuestJournalEntryResponseBuilderConfiguration questJournalEntryResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(questJournalEntryResponseBuilderConfiguration.getUserEntity());

        response.setData("journalEntryInfo", questJournalEntryPartialResponseBuilder.build(questJournalEntryResponseBuilderConfiguration));

        return response;
    }
}
