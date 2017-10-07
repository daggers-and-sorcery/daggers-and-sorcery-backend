package com.morethanheroic.swords.spell.service;

import com.morethanheroic.swords.spell.repository.domain.SpellMapper;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpellLearningService {

    private final SpellMapper spellMapper;

    public void learnSpell(UserEntity userEntity, SpellDefinition spellDefinition) {
        spellMapper.learnSpell(userEntity.getId(), spellDefinition.getId());
    }

    public boolean hasSpellLearned(UserEntity userEntity, SpellDefinition spellDefinition) {
        return spellMapper.hasSpell(userEntity.getId(), spellDefinition.getId()) > 0;
    }
}
