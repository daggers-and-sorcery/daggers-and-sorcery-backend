package com.morethanheroic.swords.spell.view.controller;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.spell.repository.domain.SpellMapper;
import com.morethanheroic.swords.spell.service.UseSpellService;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import com.morethanheroic.swords.spell.service.response.CastSpellResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CastSpellController {

    private final UseSpellService useSpellService;
    private final SpellDefinitionCache spellDefinitionCache;
    private final SpellMapper spellMapper;
    private final CastSpellResponseBuilder castSpellResponseBuilder;

    @Transactional
    @RequestMapping("/spell/cast/{spellId}")
    @SuppressWarnings("unchecked")
    public CharacterRefreshResponse castSpell(UserEntity userEntity, SessionEntity sessionEntity, @RequestParam Map<String, String> allRequestParams, @PathVariable int spellId) {
        final SpellDefinition spellDefinition = spellDefinitionCache.getSpellDefinition(spellId);

        if (spellDefinition != null && spellMapper.hasSpell(userEntity.getId(), spellId) > 0 && useSpellService.canUseSpell(userEntity, spellDefinition)) {
            useSpellService.useSpell(userEntity, spellDefinition, new CombatEffectDataHolder((Map) allRequestParams, sessionEntity));

            return castSpellResponseBuilder.build(userEntity, true);
        }

        return castSpellResponseBuilder.build(userEntity, false);
    }
}
