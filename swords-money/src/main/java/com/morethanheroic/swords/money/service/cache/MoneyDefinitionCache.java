package com.morethanheroic.swords.money.service.cache;

import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.money.domain.MoneyDefinition;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.money.service.loader.MoneyDefinitionLoader;
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

/**
 * Store the {@link MoneyDefinition}es in a cached manner.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MoneyDefinitionCache implements DefinitionCache<MoneyType, MoneyDefinition> {

    @NonNull
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
}
