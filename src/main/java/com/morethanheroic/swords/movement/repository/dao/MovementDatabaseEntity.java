package com.morethanheroic.swords.movement.repository.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovementDatabaseEntity {

    private int userId;
    private int mapId;
    private int x;
    private int y;
}
