package com.morethanheroic.swords.money.service;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.money.domain.ConversionDefinition;
import com.morethanheroic.swords.money.domain.Money;
import com.morethanheroic.swords.money.domain.MoneyCalculationQuery;
import com.morethanheroic.swords.money.domain.MoneyDefinition;
import com.morethanheroic.swords.money.service.cache.MoneyDefinitionCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link MoneyFacade}.
 */
@RunWith(MockitoJUnitRunner.class)
public class MoneyFacadeTest {

    private static final int BRONZE_COIN_ID = 1;
    private static final int SILVER_COIN_ID = 2;
    private static final int GOLD_COIN_ID = 3;
    private static final int PLATINUM_COIN_ID = 4;

    private static final int BRONZE_CONVERSION_RATE = 1;
    private static final int SILVER_CONVERSION_RATE = 100;
    private static final int GOLD_CONVERSION_RATE = 1000;
    private static final int PLATINUM_CONVERSION_RATE = 10000;

    @Mock
    private MoneyDefinitionCache moneyDefinitionCache;

    @InjectMocks
    private MoneyFacade underTest;

    @Test
    @SuppressWarnings("checkstyle:magicnumber")
    public void testGetMoneyAmountShouldReturnCorrectAmount() throws Exception {
        final MoneyCalculationQuery moneyCalculationQuery = buildMoneyCalculationQueryMock(3102, 12, 34, 2);
        when(moneyDefinitionCache.getDefinition(Money.MONEY)).thenReturn(buildMoneyDefinitionMock());

        final int result = underTest.getMoneyAmount(Money.MONEY, moneyCalculationQuery);

        //3102 + 12 * 100 + 34 * 1000 + 2 * 10000
        assertThat(result, is(58302));
    }

    //TODO: Add increase and decrease tests!

    private MoneyDefinition buildMoneyDefinitionMock() {
        return MoneyDefinition.builder()
                .id(Money.MONEY)
                .conversionDefinitions(Lists.newArrayList(
                        ConversionDefinition.builder()
                                .targetId(BRONZE_COIN_ID)
                                .conversionRate(BRONZE_CONVERSION_RATE)
                                .build(),
                        ConversionDefinition.builder()
                                .targetId(SILVER_COIN_ID)
                                .conversionRate(SILVER_CONVERSION_RATE)
                                .build(),
                        ConversionDefinition.builder()
                                .targetId(GOLD_COIN_ID)
                                .conversionRate(GOLD_CONVERSION_RATE)
                                .build(),
                        ConversionDefinition.builder()
                                .targetId(PLATINUM_COIN_ID)
                                .conversionRate(PLATINUM_CONVERSION_RATE)
                                .build()
                )).build();
    }

    private MoneyCalculationQuery buildMoneyCalculationQueryMock(int bronzeCount, int silverCount, int goldCount, int platinumCount) {
        final MoneyCalculationQuery moneyCalculationQuery = new MoneyCalculationQuery();

        moneyCalculationQuery.setCurrency(BRONZE_COIN_ID, bronzeCount);
        moneyCalculationQuery.setCurrency(SILVER_COIN_ID, silverCount);
        moneyCalculationQuery.setCurrency(GOLD_COIN_ID, goldCount);
        moneyCalculationQuery.setCurrency(PLATINUM_COIN_ID, platinumCount);

        return moneyCalculationQuery;
    }
}
