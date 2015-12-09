package com.morethanheroic.swords.money.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.morethanheroic.swords.money.domain.ConversionDefinition;
import com.morethanheroic.swords.money.domain.Money;
import com.morethanheroic.swords.money.domain.MoneyCalculationQuery;
import com.morethanheroic.swords.money.domain.MoneyCalculationResult;
import com.morethanheroic.swords.money.domain.MoneyDefinition;
import com.morethanheroic.swords.money.service.cache.MoneyDefinitionCache;
import com.sun.istack.internal.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * An easy to use API for all money functions.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MoneyFacade {

    @NotNull
    private final MoneyDefinitionCache moneyDefinitionCache;

    public MoneyDefinition getDefinition(Money money) {
        return moneyDefinitionCache.getDefinition(money);
    }

    public int getMoneyAmount(Money money, MoneyCalculationQuery moneyCalculationQuery) {
        final MoneyDefinition moneyDefinition = getDefinition(money);

        int result = 0;
        for (ConversionDefinition currencyDefinition : moneyDefinition.getConversionDefinitions()) {
            result += currencyDefinition.getConversionRate() * moneyCalculationQuery.getCurrency(currencyDefinition.getTargetId());
        }

        return result;
    }

    public MoneyCalculationResult divideMoneyAmount(Money money, int amount) {
        final MoneyDefinition moneyDefinition = getDefinition(money);

        final MoneyCalculationResult moneyCalculationResult = new MoneyCalculationResult();
        for (ConversionDefinition currencyDefinition : Lists.reverse(moneyDefinition.getConversionDefinitions())) {
            final int amountToGetFromCurrency = amount / currencyDefinition.getConversionRate();

            moneyCalculationResult.setCurrency(currencyDefinition.getTargetId(), amountToGetFromCurrency);

            amount -= amountToGetFromCurrency * currencyDefinition.getConversionRate();
        }

        return moneyCalculationResult;
    }

    public MoneyCalculationResult decreaseMoneyAmount(Money money, MoneyCalculationQuery moneyCalculationQuery, int amount) {
        Preconditions.checkArgument(amount >= 0, "Amount must be positive!");

        return divideMoneyAmount(money, getMoneyAmount(money, moneyCalculationQuery) - amount);
    }

    public MoneyCalculationResult increaseMoneyAmount(Money money, MoneyCalculationQuery moneyCalculationQuery, int amount) {
        Preconditions.checkArgument(amount >= 0, "Amount must be positive!");

        return divideMoneyAmount(money, getMoneyAmount(money, moneyCalculationQuery) + amount);
    }
}
