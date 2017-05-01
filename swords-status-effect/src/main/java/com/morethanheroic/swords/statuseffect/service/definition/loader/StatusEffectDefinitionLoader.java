package com.morethanheroic.swords.statuseffect.service.definition.loader;

import com.morethanheroic.swords.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.transformer.StatusEffectDefinitionTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Load the {@link com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectDefinition}s.
 */
@Service
@RequiredArgsConstructor
public class StatusEffectDefinitionLoader implements DefinitionLoader<StatusEffectDefinition> {

    private final StatusEffectDefinitionTransformer statusEffectDefinitionTransformer;

    @Override
    public List<StatusEffectDefinition> loadDefinitions() {
        //TODO
        return null;
    }
}
