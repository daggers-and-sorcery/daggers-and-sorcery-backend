package com.morethanheroic.swords.combat.domain;

import java.util.HashMap;
import java.util.Map;

public class CombatMessage {

    private final Map<String, Object> data = new HashMap<>();

    public void addData(String name, Object value) {
        data.put(name, value);
    }

    public Map<String, Object> getMessageData() {
        return data;
    }
}
