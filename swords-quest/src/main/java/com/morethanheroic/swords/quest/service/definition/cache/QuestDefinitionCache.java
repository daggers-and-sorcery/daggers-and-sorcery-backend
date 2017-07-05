package com.morethanheroic.swords.quest.service.definition.cache;

import com.google.common.collect.ImmutableMap;
import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.quest.service.definition.loader.QuestDefinitionLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

@Slf4j
@Service
public class QuestDefinitionCache implements DefinitionCache<Integer, QuestDefinition> {

    private final Map<Integer, QuestDefinition> questDefinitionMap;

    public QuestDefinitionCache(final QuestDefinitionLoader questDefinitionLoader) {
        questDefinitionMap = questDefinitionLoader.loadDefinitions().stream()
                .collect(collectingAndThen(Collectors.toMap(QuestDefinition::getId, Function.identity()), ImmutableMap::copyOf));

        log.info("Loaded " + questDefinitionMap.size() + " quest definitions.");
    }

    @Override
    public QuestDefinition getDefinition(final Integer id) {
        return questDefinitionMap.get(id);
    }

    @Override
    public int getSize() {
        return questDefinitionMap.size();
    }

    @Override
    public List<QuestDefinition> getDefinitions() {
        return Collections.unmodifiableList(new ArrayList<>(questDefinitionMap.values()));
    }

    @Override
    public boolean isDefinitionExists(final Integer id) {
        return questDefinitionMap.containsKey(id);
    }
}
