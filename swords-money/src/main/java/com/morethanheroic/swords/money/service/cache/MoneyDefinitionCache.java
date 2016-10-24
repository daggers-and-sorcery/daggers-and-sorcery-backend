package com.morethanheroic.swords.money.service.cache;

import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.money.domain.MoneyDefinition;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.money.service.loader.MoneyDefinitionLoader;
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

/**
 * Store the {@link MoneyDefinition}es in a cached manner.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MoneyDefinitionCache implements DefinitionCache<MoneyType, MoneyDefinition> {

    private final MoneyDefinitionLoader moneyDefinitionLoader;

    private Map<MoneyType, MoneyDefinition> moneyDefinitionMap = new EnumMap<>(MoneyType.class);

    @PostConstruct
    private void initialize() throws IOException {
        final List<MoneyDefinition> moneyDefinitionList = moneyDefinitionLoader.loadDefinitions();

        log.info("Loaded " + moneyDefinitionList.size() + " money definitions.");

        for (MoneyDefinition moneyDefinition : moneyDefinitionList) {
            moneyDefinitionMap.put(moneyDefinition.getId(), moneyDefinition);
        }
    }

    @Override
    public MoneyDefinition getDefinition(MoneyType moneyType) {
        return moneyDefinitionMap.get(moneyType);
    }

    @Override
    public int getSize() {
        return moneyDefinitionMap.size();
    }

    @Override
    public List<MoneyDefinition> getDefinitions() {
        return Collections.unmodifiableList(new ArrayList<>(moneyDefinitionMap.values()));
    }

    @Override
    public boolean isDefinitionExists(final MoneyType key) {
        return moneyDefinitionMap.containsKey(key);
    }
}
