package com.morethanheroic.swords.attribute.service.cache;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttributeDefinition;
import com.morethanheroic.swords.attribute.service.loader.SkillAttributeDefinitionLoader;
import com.morethanheroic.swords.definition.cache.DefinitionCache;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SkillAttributeDefinitionCache implements DefinitionCache<SkillAttribute, SkillAttributeDefinition> {

    private Map<SkillAttribute, SkillAttributeDefinition> skillAttributeDefinitionMap = new EnumMap<>(SkillAttribute.class);

    @NonNull
    private final SkillAttributeDefinitionLoader skillAttributeDefinitionLoader;

    @PostConstruct
    private void initialize() throws IOException {
        final List<SkillAttributeDefinition> skillAttributeDefinitions = skillAttributeDefinitionLoader.loadDefinitions();

        log.info("Loaded " + skillAttributeDefinitions.size() + " skill attribute entity definitions.");

        for (SkillAttributeDefinition skillAttributeDefinition : skillAttributeDefinitions) {
            skillAttributeDefinitionMap.put(skillAttributeDefinition.getSkillAttribute(), skillAttributeDefinition);
        }
    }

    @Override
    public SkillAttributeDefinition getDefinition(SkillAttribute key) {
        return skillAttributeDefinitionMap.get(key);
    }
}
