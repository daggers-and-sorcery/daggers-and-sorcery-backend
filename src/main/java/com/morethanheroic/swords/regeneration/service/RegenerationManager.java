package com.morethanheroic.swords.regeneration.service;

import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegenerationManager {

    public void regenerate(UserEntity user) {
        user.regenerate(10, 10, 10, new Date());
    }
}
