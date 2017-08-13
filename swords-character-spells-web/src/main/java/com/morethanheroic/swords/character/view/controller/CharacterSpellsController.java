package com.morethanheroic.swords.character.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.character.view.response.service.CharacterSpellsResponseBuilder;
import com.morethanheroic.swords.character.view.response.service.domain.CharacterSpellsResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CharacterSpellsController {

    private final CharacterSpellsResponseBuilder characterSpellsResponseBuilder;

    @GetMapping("/character/spells")
    public Response spells(final UserEntity userEntity) {
        return characterSpellsResponseBuilder.build(
                CharacterSpellsResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }
}
