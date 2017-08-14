package com.morethanheroic.swords.spell.domain;

import com.morethanheroic.entity.domain.Entity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SpellEntity implements Entity {

    private final SpellDefinition spellDefinition;

    @Override
    public int getId() {
        return spellDefinition.getId();
    }
}
