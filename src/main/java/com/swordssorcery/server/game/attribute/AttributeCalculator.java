package com.swordssorcery.server.game.attribute;

import com.swordssorcery.server.game.attribute.data.AttributeData;
import com.swordssorcery.server.model.db.user.UserDatabaseEntity;
import com.swordssorcery.server.model.entity.user.UserEntity;

public interface AttributeCalculator {

    AttributeData calculateAttributeValue(UserEntity user, Attribute attribute);
}
