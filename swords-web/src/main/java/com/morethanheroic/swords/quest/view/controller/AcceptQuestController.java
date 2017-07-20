package com.morethanheroic.swords.quest.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import com.morethanheroic.swords.quest.service.initialize.QuestInitializer;
import com.morethanheroic.swords.quest.view.request.domain.AcceptQuestRequest;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AcceptQuestController {

    private final QuestDefinitionCache questDefinitionCache;
    private final ResponseFactory responseFactory;
    private final QuestInitializer questInitializer;

    @PostMapping("/quest/accept")
    public Response acceptQuest(final UserEntity userEntity, @RequestBody @Valid final AcceptQuestRequest acceptQuestRequest) {
        final QuestDefinition questDefinition = questDefinitionCache.getDefinition(acceptQuestRequest.getQuestId());

        questInitializer.startQuest(userEntity, questDefinition);

        return responseFactory.successfulResponse(userEntity);
    }
}
