package com.morethanheroic.swords.money.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.morethanheroic.swords.money.domain.Conversion;
import com.morethanheroic.swords.money.domain.MoneyCalculationQuery;
import com.morethanheroic.swords.money.domain.MoneyCalculationResult;
import com.morethanheroic.swords.money.domain.MoneyDefinition;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.money.service.cache.MoneyDefinitionCache;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * An easy to use API for all money functions.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MoneyFacade {

    @NonNull
    private final MoneyDefinitionCache moneyDefinitionCache;

    public MoneyDefinition getDefinition(MoneyType moneyType) {
        return moneyDefinitionCache.getDefinition(moneyType);
    }

    public int getMoneyAmount(MoneyType moneyType, MoneyCalculationQuery moneyCalculationQuery) {
        final MoneyDefinition moneyDefinition = getDefinition(moneyType);

        int result = 0;
        for (Conversion currencyDefinition : moneyDefinition.getConversions()) {
            result += currencyDefinition.getConversionRate() * moneyCalculationQuery.getCurrency(currencyDefinition.getTargetId());
        }

        return result;
    }

    public MoneyCalculationResult divideMoneyAmount(MoneyType moneyType, int amount) {
        final MoneyDefinition moneyDefinition = getDefinition(moneyType);

        final MoneyCalculationResult moneyCalculationResult = new MoneyCalculationResult();
        for (Conversion currencyDefinition : Lists.reverse(moneyDefinition.getConversions())) {
            final int amountToGetFromCurrency = amount / currencyDefinition.getConversionRate();

            moneyCalculationResult.setCurrency(currencyDefinition.getTargetId(), amountToGetFromCurrency);

            amount -= amountToGetFromCurrency * currencyDefinition.getConversionRate();
        }

        return moneyCalculationResult;
    }

    public MoneyCalculationResult decreaseMoneyAmount(MoneyType moneyType, MoneyCalculationQuery moneyCalculationQuery, int amount) {
        Preconditions.checkArgument(amount >= 0, "Amount must be positive!");

        return divideMoneyAmount(moneyType, getMoneyAmount(moneyType, moneyCalculationQuery) - amount);
    }

    public MoneyCalculationResult increaseMoneyAmount(MoneyType moneyType, MoneyCalculationQuery moneyCalculationQuery, int amount) {
        Preconditions.checkArgument(amount >= 0, "Amount must be positive!");

        return divideMoneyAmount(moneyType, getMoneyAmount(moneyType, moneyCalculationQuery) + amount);
    }
}
