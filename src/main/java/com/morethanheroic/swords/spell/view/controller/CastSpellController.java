package com.morethanheroic.swords.spell.view.controller;

import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.spell.repository.domain.SpellMapper;
import com.morethanheroic.swords.spell.service.response.CastSpellResponseBuilder;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.spell.service.UseSpellService;
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
    private final SpellDefinitionCache spellDefinitionCache;
    private final SpellMapper spellMapper;
    private final CastSpellResponseBuilder castSpellResponseBuilder;

    @Autowired
    public CastSpellController(UseSpellService useSpellService, SpellDefinitionCache spellDefinitionCache, SpellMapper spellMapper, CastSpellResponseBuilder castSpellResponseBuilder) {
        this.useSpellService = useSpellService;
        this.spellDefinitionCache = spellDefinitionCache;
        this.spellMapper = spellMapper;
        this.castSpellResponseBuilder = castSpellResponseBuilder;
    }

    @Transactional
    @RequestMapping(value = "/spell/cast/{spellId}", method = RequestMethod.GET)
    public Response castSpell(UserEntity userEntity, @PathVariable int spellId) {
        SpellDefinition spellDefinition = spellDefinitionCache.getSpellDefinition(spellId);

        if (spellDefinition != null && spellMapper.hasSpell(userEntity.getId(), spellId) > 0 && useSpellService.canUseSpell(userEntity, spellDefinition)) {
            useSpellService.useSpell(userEntity, spellDefinition);

            return castSpellResponseBuilder.build(userEntity, true);
        }

        return castSpellResponseBuilder.build(userEntity, false);
    }
}
