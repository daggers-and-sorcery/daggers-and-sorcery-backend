package com.morethanheroic.swords.money.service.cache;

import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.money.domain.Money;
import com.morethanheroic.swords.money.domain.MoneyDefinition;
import com.morethanheroic.swords.money.service.loader.MoneyDefinitionLoader;
import com.sun.istack.internal.NotNull;
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
public class MoneyDefinitionCache implements DefinitionCache<Money, MoneyDefinition> {

    private Map<Money, MoneyDefinition> moneyDefinitionMap = new EnumMap<>(Money.class);

    @NotNull
    private final MoneyDefinitionLoader moneyDefinitionLoader;

    @PostConstruct
    private void initialize() throws IOException {
        final List<MoneyDefinition> moneyDefinitionList = moneyDefinitionLoader.loadDefinitions();

        log.info("Loaded " + moneyDefinitionList.size() + " money definitions.");

        for (MoneyDefinition moneyDefinition : moneyDefinitionList) {
            moneyDefinitionMap.put(moneyDefinition.getId(), moneyDefinition);
        }
    }

    @Override
    public MoneyDefinition getDefinition(Money money) {
        return moneyDefinitionMap.get(money);
    }
}
