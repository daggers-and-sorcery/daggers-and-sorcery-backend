package com.morethanheroic.swords.character.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.character.view.response.service.domain.CharacterSpellsResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterSpellsResponseBuilder implements ResponseBuilder<CharacterSpellsResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final CharacterSpellsSpellPartialResponseBuilder characterSpellsSpellPartialResponseBuilder;

    @Override
    public Response build(final CharacterSpellsResponseBuilderConfiguration characterSpellsResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(characterSpellsResponseBuilderConfiguration.getUserEntity());

        response.setData("spells", characterSpellsSpellPartialResponseBuilder.build(characterSpellsResponseBuilderConfiguration));

        return response;
    }
}
