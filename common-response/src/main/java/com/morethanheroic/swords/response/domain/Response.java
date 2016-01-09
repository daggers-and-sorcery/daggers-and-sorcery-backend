package com.morethanheroic.swords.response.domain;

import java.util.HashMap;
import java.util.Map;

public class Response {

    private final Map<String, Object> data = new HashMap<>();

    //Refactor everything to only allow ResponsePartial to be set for the main response!
    public void setData(String name, Object value) {
        data.put(name, value);
    }

    public Object getData(String name) {
        return data.get(name);
    }

    public Map<String, Object> getDataMap() {
        return data;
    }
}
