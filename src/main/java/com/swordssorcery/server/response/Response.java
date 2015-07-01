package com.swordssorcery.server.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.HashMap;

@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE
)
public class Response {

    private HashMap<String, Object> data = new HashMap<>();

    public void setData(String name, Object value) {
        data.put(name, value);
    }

    public Object getData(String name) {
        return data.get(name);
    }
}
