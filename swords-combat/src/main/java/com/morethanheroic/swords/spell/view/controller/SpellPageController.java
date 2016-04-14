package com.morethanheroic.swords.spell.view.controller;

import com.morethanheroic.response.exception.NotFoundException;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import com.morethanheroic.swords.spell.service.page.PageEntryDataContainer;
import com.morethanheroic.swords.spell.service.page.SpellPageRegistry;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SpellPageController {

    @Autowired
    private SpellPageRegistry spellPageRegistry;

    @Autowired
    private SpellDefinitionCache spellDefinitionCache;

    @RequestMapping(value = "/spell/page/{spellId}", method = RequestMethod.GET)
    public CharacterRefreshResponse castSpell(UserEntity userEntity, SessionEntity sessionEntity, @PathVariable int spellId) {
        if (!spellDefinitionCache.isSpellDefinitionExists(spellId)) {
            throw new NotFoundException();
        }

        return spellPageRegistry.getSpellPageEntry(spellDefinitionCache.getSpellDefinition(spellId)).build(new PageEntryDataContainer(userEntity, sessionEntity));
    }
}
