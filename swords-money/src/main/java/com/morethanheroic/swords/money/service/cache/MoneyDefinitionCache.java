package com.morethanheroic.swords.money.service.cache;

import com.morethanheroic.swords.money.domain.Money;
import com.morethanheroic.swords.money.domain.MoneyDefinition;
import com.morethanheroic.swords.money.service.loader.MoneyDefinitionLoader;
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
public class MoneyDefinitionCache {

    private Map<Money, MoneyDefinition> moneyDefinitionMap = new EnumMap<>(Money.class);

    @Autowired
    private MoneyDefinitionLoader moneyDefinitionLoader;

    @PostConstruct
    private void initialize() throws IOException {
        final List<MoneyDefinition> moneyDefinitionList = moneyDefinitionLoader.loadMoneyDefinitions();

        log.info("Loaded " + moneyDefinitionList.size() + " money definitions.");

        for (MoneyDefinition moneyDefinition : moneyDefinitionList) {
            moneyDefinitionMap.put(moneyDefinition.getId(), moneyDefinition);
        }
    }

    public MoneyDefinition getMoneyDefinition(Money money) {
        return moneyDefinitionMap.get(money);
    }
}
