package com.morethanheroic.swords.spell.view.controller;

import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.spell.repository.domain.SpellMapper;
import com.morethanheroic.swords.spell.service.CastSpellResponseBuilder;
import com.morethanheroic.swords.spell.service.SpellDefinitionManager;
import com.morethanheroic.swords.spell.service.domain.SpellDefinition;
import com.morethanheroic.swords.spell.service.domain.UseSpellService;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CastSpellController {

    private final UseSpellService useSpellService;
    private final SpellDefinitionManager spellDefinitionManager;
    private final SpellMapper spellMapper;
    private final CastSpellResponseBuilder castSpellResponseBuilder;

    @Autowired
    public CastSpellController(UseSpellService useSpellService, SpellDefinitionManager spellDefinitionManager, SpellMapper spellMapper, CastSpellResponseBuilder castSpellResponseBuilder) {
        this.useSpellService = useSpellService;
        this.spellDefinitionManager = spellDefinitionManager;
        this.spellMapper = spellMapper;
        this.castSpellResponseBuilder = castSpellResponseBuilder;
    }

    @Transactional
    @RequestMapping(value = "/spell/cast/{spellId}", method = RequestMethod.GET)
    public Response castSpell(UserEntity userEntity, @PathVariable int spellId) {
        SpellDefinition spellDefinition = spellDefinitionManager.getSpellDefinition(spellId);

        if (spellDefinition != null && spellMapper.hasSpell(userEntity.getId(), spellId) > 0 && useSpellService.canUseSpell(userEntity, spellDefinition)) {
            useSpellService.useSpell(userEntity, spellDefinition);

            return castSpellResponseBuilder.build(userEntity, true);
        }

        return castSpellResponseBuilder.build(userEntity, false);
    }
}
