package com.morethanheroic.swords.combat.domain;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class CombatEffectDataHolder {

    private final Map<String, Object> parameters;
    private final HttpSession httpSession;

    public CombatEffectDataHolder(Map<String, Object> parameters, HttpSession httpSession) {
        this.parameters = parameters;
        this.httpSession = httpSession;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }
}
