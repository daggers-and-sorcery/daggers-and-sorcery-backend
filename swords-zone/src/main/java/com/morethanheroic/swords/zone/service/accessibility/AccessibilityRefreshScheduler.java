package com.morethanheroic.swords.zone.service.accessibility;

import com.morethanheroic.swords.metadata.service.MetadataPurger;
import com.morethanheroic.swords.metadata.service.cache.MetadataDefinitionCache;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccessibilityRefreshScheduler {

    private static final String ACCESSIBILITY_METADATA_ID_PREFIX = "ZONE_ACCESSIBILITY_";

    private final MetadataDefinitionCache metadataDefinitionCache;
    private final MetadataPurger metadataPurger;

    @Scheduled(cron = "0 0 12 * * *")
    public void refreshAccessibility() {
        metadataDefinitionCache.getDefinitions().stream()
                .filter(metadataDefinition -> metadataDefinition.getName().startsWith(ACCESSIBILITY_METADATA_ID_PREFIX))
                .forEach(metadataPurger::removeAllMetadata);
    }
}
