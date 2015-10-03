package com.morethanheroic.swords.combatsettings.service;

import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.common.response.ResponseFactory;
import com.morethanheroic.swords.spell.repository.dao.SpellDatabaseEntity;
import com.morethanheroic.swords.spell.repository.domain.SpellMapper;
import com.morethanheroic.swords.spell.service.SpellDefinitionManager;
import com.morethanheroic.swords.spell.service.domain.SpellDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UsableSpellsResponseBuilder {

    private final ResponseFactory responseFactory;
    private final SpellMapper spellMapper;
    private final SpellDefinitionManager spellDefinitionManager;

    @Autowired
    public UsableSpellsResponseBuilder(ResponseFactory responseFactory, SpellMapper spellMapper, SpellDefinitionManager spellDefinitionManager) {
        this.responseFactory = responseFactory;
        this.spellMapper = spellMapper;
        this.spellDefinitionManager = spellDefinitionManager;
    }

    public Response build(UserEntity userEntity) {
        Response response =  responseFactory.newResponse(userEntity);
        ArrayList<HashMap<String, Object>> spelllist = new ArrayList<>();

        List<SpellDatabaseEntity> spells = spellMapper.getAllSpellsForUser(userEntity.getId());

        for(SpellDatabaseEntity spell : spells) {
            SpellDefinition spellDefinition = spellDefinitionManager.getSpellDefinition(spell.getSpellId());
            HashMap<String, Object> spellInfo = new HashMap<>();

            spellInfo.put("id", spellDefinition.getId());
            spellInfo.put("name", spellDefinition.getName());

            spelllist.add(spellInfo);
        }

        response.setData("spellList", spelllist);

        return response;
    }
}
