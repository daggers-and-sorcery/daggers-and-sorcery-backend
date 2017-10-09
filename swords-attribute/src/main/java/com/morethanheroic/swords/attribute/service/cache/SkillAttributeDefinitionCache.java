package com.morethanheroic.swords.attribute.service.cache;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttributeDefinition;
import com.morethanheroic.swords.attribute.service.loader.SkillAttributeDefinitionLoader;
import com.morethanheroic.definition.cache.DefinitionCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class SkillAttributeDefinitionCache implements DefinitionCache<SkillAttribute, SkillAttributeDefinition> {

    private Map<SkillAttribute, SkillAttributeDefinition> skillAttributeDefinitionMap = new EnumMap<>(SkillAttribute.class);

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

    @Override
    public int getSize() {
        return skillAttributeDefinitionMap.size();
    }

    @Override
    public List<SkillAttributeDefinition> getDefinitions() {
        return Collections.unmodifiableList(new ArrayList<>(skillAttributeDefinitionMap.values()));
    }

    @Override
    public boolean isDefinitionExists(SkillAttribute key) {
        return skillAttributeDefinitionMap.containsKey(key);
    }
}
