package com.morethanheroic.swords.skill.scavenging.view.controller;

import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.scavenging.domain.ScavengingEntity;
import com.morethanheroic.swords.scavenging.service.ScavengingEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.beans.Transient;

@RestController
public class ScavengingController {

    private static final int MOVEMENT_TO_SCAVENGING_POINT_CONVERSION_RATE = 5;
    private static final int MAX_SCAVENGING_POINTS = 50;

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private ScavengingEntityFactory scavengingEntityFactory;

    @Transactional
    @RequestMapping(value = "/skill/scavenging/convert", method = RequestMethod.POST)
    public CharacterRefreshResponse convertMovementPoints(UserEntity user, @RequestParam int pointsToConvert) {
        final CharacterRefreshResponse response = responseFactory.newResponse(user);
        final ScavengingEntity scavengingEntity = scavengingEntityFactory.getEntity(user);
        final int actualScavengingPoints = scavengingEntity.getScavengingPoint();

        if (actualScavengingPoints + pointsToConvert * MOVEMENT_TO_SCAVENGING_POINT_CONVERSION_RATE > MAX_SCAVENGING_POINTS) {
            pointsToConvert = (int) Math.floor((MAX_SCAVENGING_POINTS - actualScavengingPoints) / 5);
        }

        if (user.getMovementPoints() >= pointsToConvert) {
            scavengingEntity.setScavengingPoint(scavengingEntity.getScavengingPoint() + pointsToConvert * MOVEMENT_TO_SCAVENGING_POINT_CONVERSION_RATE);

            user.setMovementPoints(user.getMovementPoints() - pointsToConvert);

            response.setData("success", true);
        } else {
            response.setData("success", false);
        }

        return response;
    }
}
