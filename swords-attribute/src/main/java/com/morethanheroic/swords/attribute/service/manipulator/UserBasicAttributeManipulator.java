package com.morethanheroic.swords.attribute.service.manipulator;

import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Let you manipulate the user's basic attributes. It will take into account the user's maximum attribute and don't let
 * you set higher amounts than that.
 */
@Service
@Transactional(propagation = Propagation.MANDATORY)
public class UserBasicAttributeManipulator {

    public void increaseHealth(final UserEntity userEntity, final int amount) {

    }

    public void decreaseHealth(final UserEntity userEntity, final int amount) {

    }

    public void increaseMana(final UserEntity userEntity, final int amount) {

    }

    public void decreaseMana(final UserEntity userEntity, final int amount) {
        
    }

    public void increaseMovement(final UserEntity userEntity, final int amount) {

    }

    public void decreaseMovement(final UserEntity userEntity, final int amount) {

    }
}
