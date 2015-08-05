package com.morethanheroic.swords.combat.domain;

import java.util.HashMap;

public class CombatMessage {

    private HashMap<String, Object> data = new HashMap<>();

    public void addData(String name, Object value) {
        data.put(name,value);
    }

    public HashMap<String, Object> getMessageData() {
        return data;
    }
}
