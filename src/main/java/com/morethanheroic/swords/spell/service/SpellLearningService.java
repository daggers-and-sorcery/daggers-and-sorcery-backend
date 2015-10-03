package com.morethanheroic.swords.spell.service;

import com.morethanheroic.swords.spell.repository.domain.SpellMapper;
import com.morethanheroic.swords.spell.service.domain.SpellDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpellLearningService {

    private final SpellMapper spellMapper;

    @Autowired
    public SpellLearningService(SpellMapper spellMapper) {
        this.spellMapper = spellMapper;
    }

    public void learnSpell(UserEntity userEntity, SpellDefinition spellDefinition) {
        spellMapper.learnSpell(userEntity.getId(), spellDefinition.getId());
    }
}
