package com.swordssorcery.server.game.attribute;

import com.swordssorcery.server.game.attribute.data.AttributeData;
import com.morethanheroic.swords.user.domain.UserEntity;

public interface AttributeCalculator {

    AttributeData calculateAttributeValue(UserEntity user, Attribute attribute);
}
