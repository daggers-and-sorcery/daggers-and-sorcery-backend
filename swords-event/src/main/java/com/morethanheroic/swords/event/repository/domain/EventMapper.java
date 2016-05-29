package com.morethanheroic.swords.event.repository.domain;

import com.morethanheroic.swords.event.domain.EventType;
import com.morethanheroic.swords.event.repository.dao.EventDatabaseEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventMapper {

    @Select("SELECT * FROM events WHERE ending_date < NOW()")
    List<EventDatabaseEntity> getEndingEvents();

    @Select("SELECT * FROM events WHERE user_id = #{userId}, event_type = #{eventType}")
    List<EventDatabaseEntity> getEventsByType(@Param("userId") int userId, @Param("eventType") EventType eventType);

    @Insert("INSERT INTO events SET user_id = #{userId}, event_id = #{eventId}, ending_date = #{ending}, eventType = #{eventType}")
    void insertEvent(EventDatabaseEntity event);
}
