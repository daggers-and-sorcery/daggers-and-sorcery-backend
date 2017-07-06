package com.morethanheroic.swords.inventory.service;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.inventory.domain.IdentificationType;
import com.morethanheroic.swords.session.SessionAttributeType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UnidentifiedItemIdCalculator {

    private final Random random;

    public IdentificationType isIdentified(final int itemId) {
        return itemId > Short.MAX_VALUE ? IdentificationType.UNIDENTIFIED : IdentificationType.IDENTIFIED;
    }

    @SuppressWarnings("unchecked")
    public int getUnidentifiedItemId(SessionEntity sessionEntity, int itemId) {
        HashMap<Integer, Integer> unidentifiedItemMap = (HashMap<Integer, Integer>) sessionEntity.getAttribute(SessionAttributeType.UNIDENTIFIED_ITEM_ID_MAP.name());

        if (unidentifiedItemMap == null) {
            unidentifiedItemMap = new HashMap<>();

            sessionEntity.setAttribute(SessionAttributeType.UNIDENTIFIED_ITEM_ID_MAP.name(), unidentifiedItemMap);
        }

        if (!unidentifiedItemMap.containsKey(itemId)) {
            unidentifiedItemMap.put(itemId, getValidKey(unidentifiedItemMap));
        }

        return unidentifiedItemMap.get(itemId);
    }

    @SuppressWarnings("unchecked")
    public int getRealItemId(SessionEntity sessionEntity, int unidentifiedId) {
        final Map<Integer, Integer> unidentifiedItemMap = (HashMap<Integer, Integer>) sessionEntity.getAttribute(SessionAttributeType.UNIDENTIFIED_ITEM_ID_MAP.name());

        if (unidentifiedItemMap != null) {
            for (Map.Entry<Integer, Integer> entry : unidentifiedItemMap.entrySet()) {
                final Integer unidentifiedMapEntryId = entry.getValue();

                if (unidentifiedMapEntryId == unidentifiedId) {
                    return entry.getKey();
                }
            }
        }

        return unidentifiedId;
    }

    private int getValidKey(HashMap<Integer, Integer> map) {
        int result = getRandomMapId();

        if (map.containsKey(result)) {
            return getValidKey(map);
        }

        return result;
    }

    private int getRandomMapId() {
        return random.nextInt(Integer.MAX_VALUE - Short.MAX_VALUE) + Short.MAX_VALUE;
    }
}
