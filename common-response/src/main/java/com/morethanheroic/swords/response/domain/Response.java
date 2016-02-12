package com.morethanheroic.swords.response.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private final Map<String, Object> data = new HashMap<>();

    /**
     * Refactor everything to only allow ResponsePartial to be set for the main response!
     */
    @Deprecated
    public void setData(String name, Object value) {
        data.put(name, value);
    }

    public void setData(String name, PartialResponse value) {
        data.put(name, value);
    }

    public void setData(String name, Collection<PartialResponse> value) {
        data.put(name, value);
    }

    public Object getData(String name) {
        return data.get(name);
    }

    public Map<String, Object> getDataMap() {
        return data;
    }
}
