package com.morethanheroic.swords.event.repository.dao;

import com.morethanheroic.swords.event.domain.EventType;
import lombok.Data;

import java.util.Date;

@Data
public class EventDatabaseEntity {

    private int id;
    private int userId;
    private int eventId;
    private EventType eventType;
    private Date endingDate;
}
