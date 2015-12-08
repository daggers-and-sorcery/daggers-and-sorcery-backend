package com.morethanheroic.swords.money.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Store the result of a money calculation.
 */
public class MoneyCalculationResult {

    private Map<Integer, Integer> currencyMap = new HashMap<>();

    public void setCurrency(int currency, int amount) {
        this.currencyMap.put(currency, amount);
    }

    public int getCurrency(int currency) {
        return currencyMap.containsKey(currency) ? currencyMap.get(currency) : 0;
    }
}
