package com.morethanheroic.swords.inventory.service;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.session.SessionAttributeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UnidentifiedItemIdCalculator {

    @Autowired
    private Random random;

    public boolean isIdentifiedItem(int itemId) {
        return !isUnidentifiedItem(itemId);
    }

    public boolean isUnidentifiedItem(int itemId) {
        return itemId > Short.MAX_VALUE;
    }

    //TODO: Move HttpSession out of this! It's a service, shouldn't know about sessions and shit like that!
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
