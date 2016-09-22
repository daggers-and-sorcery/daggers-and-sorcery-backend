package com.morethanheroic.swords.explore.service.event.evaluator.attempt.result;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.attempt.domain.AttributeAttemptCalculationResult;
import com.morethanheroic.swords.explore.service.event.evaluator.attempt.result.factory.AttributeAttemptEntryResultFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttributeAttemptEntryResultFactoryProvider {

    private static final Attribute DEFAULT_RESULT_FACTORY_ID = null;

    private final Map<Attribute, AttributeAttemptEntryResultFactory> entryResultFactoryMap;

    public AttributeAttemptEntryResultFactoryProvider(final List<AttributeAttemptEntryResultFactory> entryResultFactoryList) {
        final Map<Attribute, AttributeAttemptEntryResultFactory> temporaryEntryResultFactoryMap = new HashMap<>();
        for (AttributeAttemptEntryResultFactory entryResultFactory : entryResultFactoryList) {
            temporaryEntryResultFactoryMap.put(entryResultFactory.supportedAttribute(), entryResultFactory);
        }
        entryResultFactoryMap = Collections.unmodifiableMap(temporaryEntryResultFactoryMap);
    }

    public AttributeAttemptEntryResultFactory getFactory(final AttributeAttemptCalculationResult attributeAttemptCalculationResult) {
        if (attributeAttemptCalculationResult.getExtensionResult() == null) {
            return entryResultFactoryMap.get(DEFAULT_RESULT_FACTORY_ID);
        }

        return entryResultFactoryMap.get(attributeAttemptCalculationResult.getExtensionResult().supportedAttribute());
    }
}
