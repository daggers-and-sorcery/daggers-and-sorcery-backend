package com.morethanheroic.swords.inventory.service;

import com.morethanheroic.swords.common.session.SessionAttributeType;
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
    public int getUnidentifiedItemId(HttpSession session, int itemId) {
        HashMap<Integer, Integer> unidentifiedItemMap = (HashMap<Integer, Integer>) session.getAttribute(SessionAttributeType.UNIDENTIFIED_ITEM_ID_MAP);

        if (unidentifiedItemMap == null) {
            unidentifiedItemMap = new HashMap<>();

            session.setAttribute(SessionAttributeType.UNIDENTIFIED_ITEM_ID_MAP, unidentifiedItemMap);
        }

        if (!unidentifiedItemMap.containsKey(itemId)) {
            unidentifiedItemMap.put(itemId, getValidKey(unidentifiedItemMap));
        }

        return unidentifiedItemMap.get(itemId);
    }

    @SuppressWarnings("unchecked")
    public int getRealItemId(HttpSession session, int unidentifiedId) {
        HashMap<Integer, Integer> unidentifiedItemMap = (HashMap<Integer, Integer>) session.getAttribute(SessionAttributeType.UNIDENTIFIED_ITEM_ID_MAP);

        if (unidentifiedItemMap != null) {
            for (Map.Entry<Integer, Integer> entry : unidentifiedItemMap.entrySet()) {
                Integer unidentifiedMapEntryId = entry.getValue();

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
