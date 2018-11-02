package com.morethanheroic.swords.money.service.conversion;

import com.morethanheroic.swords.money.domain.Conversion;
import org.springframework.stereotype.Service;

import java.util.Comparator;

/**
 * Compare {@link Conversion} entities by conversion rate.
 */
@Service
public class ConversionRateComparator implements Comparator<Conversion> {

    @Override
    public int compare(Conversion o1, Conversion o2) {
        if (o1.getConversionRate() > o2.getConversionRate()) {
            return 1;
        } else if (o2.getConversionRate() > o1.getConversionRate()) {
            return -1;
        }

        return 0;
    }
}
