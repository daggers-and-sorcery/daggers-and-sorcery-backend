package com.morethanheroic.swords.skill.herblore.service.gathering;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.service.calc.BasicAttributeCalculator;
import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.event.domain.EventEntity;
import com.morethanheroic.swords.skill.herblore.service.gathering.domain.GatheringResult;
import com.morethanheroic.swords.event.domain.EventType;
import com.morethanheroic.swords.event.service.EventRegistry;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides an entry point for the herb gathering functionality of the Herblore skill.
 */
@Service
@RequiredArgsConstructor
public class GatheringService {

    private static final int MAXIMUM_GATHERING_QUEUE_COUNT = 1;
    private static final int GATHERING_MOVEMENT_POINT_COST = 1;
    private static final int GATHERING_EVENT_ID = 4;

    private final EventRegistry eventRegistry;
    private final BasicAttributeCalculator basicAttributeCalculator;
    private final UserBasicAttributeManipulator userBasicAttributeManipulator;

    /**
     * Start the gathering process for the user.
     *
     * @param userEntity the user to start gathering to
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public GatheringResult gather(final UserEntity userEntity) {
        if (eventRegistry.getRegisteredEventCount(userEntity, EventType.HERBLORE_GATHERING) >= MAXIMUM_GATHERING_QUEUE_COUNT) {
            return GatheringResult.QUEUE_FULL;
        }

        if (basicAttributeCalculator.calculateActualValue(userEntity, BasicAttribute.MOVEMENT, false).getValue() < GATHERING_MOVEMENT_POINT_COST) {
            return GatheringResult.NOT_ENOUGH_MOVEMENT;
        }

        final EventEntity eventEntity = EventEntity.builder()
                .eventId(GATHERING_EVENT_ID)
                .user(userEntity)
                .build();

        eventRegistry.registerEvent(eventEntity);
        userBasicAttributeManipulator.decreaseMovement(userEntity, GATHERING_MOVEMENT_POINT_COST);

        return GatheringResult.SUCCESSFUL;
    }
}
