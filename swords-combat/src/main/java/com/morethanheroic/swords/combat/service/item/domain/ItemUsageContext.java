package com.morethanheroic.swords.combat.service.item.domain;

import com.morethanheroic.session.domain.SessionEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class ItemUsageContext {

    private final Map<String, String> parameters;
    private final SessionEntity sessionEntity;
}
