package com.morethanheroic.swords.combat.view.response.service.usable;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.spell.repository.dao.SpellDatabaseEntity;
import com.morethanheroic.swords.spell.repository.domain.SpellMapper;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsableSpellsResponseBuilder {

    private final ResponseFactory responseFactory;
    private final SpellMapper spellMapper;
    private final SpellDefinitionCache spellDefinitionCache;

    public Response build(UserEntity userEntity) {
        final Response response =  responseFactory.newResponse(userEntity);
        final List<HashMap<String, Object>> spelllist = new ArrayList<>();

        List<SpellDatabaseEntity> spells = spellMapper.getAllSpellsForUser(userEntity.getId());

        for(SpellDatabaseEntity spell : spells) {
            SpellDefinition spellDefinition = spellDefinitionCache.getSpellDefinition(spell.getSpellId());

            if(spellDefinition.isCombatSpell()) {
                HashMap<String, Object> spellInfo = new HashMap<>();

                spellInfo.put("id", spellDefinition.getId());
                spellInfo.put("name", spellDefinition.getName());

                spelllist.add(spellInfo);
            }
        }

        response.setData("spellList", spelllist);

        return response;
    }
}
