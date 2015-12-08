package com.morethanheroic.swords.money.service.conversion;

import com.morethanheroic.swords.money.domain.ConversionDefinition;
import org.springframework.stereotype.Service;

import java.util.Comparator;

/**
 * Compare {@link ConversionDefinition} entities by conversion rate.
 */
@Service
public class ConversionRateComparator implements Comparator<ConversionDefinition> {

    @Override
    public int compare(ConversionDefinition o1, ConversionDefinition o2) {
        if (o1.getConversionRate() > o2.getConversionRate()) {
            return 1;
        } else if (o2.getConversionRate() > o1.getConversionRate()) {
            return -1;
        }

        return 0;
    }
}
