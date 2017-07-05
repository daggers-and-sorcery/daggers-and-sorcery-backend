package com.morethanheroic.swords.witchhuntersguild.view.quest.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.witchhuntersguild.view.quest.response.service.domain.WitchhuntersGuildQuestResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Witchhunters guild quest response builder.
 */
@Service
@RequiredArgsConstructor
public class WitchhuntersGuildQuestResponseBuilder implements ResponseBuilder<WitchhuntersGuildQuestResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final WitchhuntersGuildQuestPartialResponseBuilder witchhuntersGuildQuestPartialResponseBuilder;

    @Override
    public Response build(final WitchhuntersGuildQuestResponseBuilderConfiguration witchhuntersGuildQuestResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(witchhuntersGuildQuestResponseBuilderConfiguration.getUserEntity());

        response.setData("quest", witchhuntersGuildQuestPartialResponseBuilder.build(witchhuntersGuildQuestResponseBuilderConfiguration));

        return response;
    }
}
