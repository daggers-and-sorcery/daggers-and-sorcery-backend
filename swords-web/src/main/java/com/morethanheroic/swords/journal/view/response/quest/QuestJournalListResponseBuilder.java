package com.morethanheroic.swords.journal.view.response.quest;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.journal.view.response.quest.domain.QuestJournalListResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestJournalListResponseBuilder implements ResponseBuilder<QuestJournalListResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final QuestJournalListEntryPartialResponseBuilder questJournalListEntryPartialResponseBuilder;

    @Override
    public Response build(QuestJournalListResponseBuilderConfiguration questJournalListResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(questJournalListResponseBuilderConfiguration.getUserEntity());

        response.setData("journalInfo", questJournalListEntryPartialResponseBuilder.build(questJournalListResponseBuilderConfiguration));

        return response;
    }
}
