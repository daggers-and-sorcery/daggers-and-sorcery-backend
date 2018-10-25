package com.morethanheroic.swords.event.repository.domain;

import com.morethanheroic.swords.event.domain.EventType;
import com.morethanheroic.swords.event.repository.dao.EventDatabaseEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventMapper {

    @Select("SELECT * FROM events WHERE ending_date < NOW()")
    List<EventDatabaseEntity> getEndingEvents();

    @Select("SELECT * FROM events WHERE user_id = #{userId} AND event_type = #{eventType}")
    List<EventDatabaseEntity> getEventsByType(@Param("userId") int userId, @Param("eventType") EventType eventType);

    @Insert("INSERT INTO events SET user_id = #{userId}, event_id = #{eventId}, ending_date = #{endingDate}, event_type = #{eventType}")
    void insertEvent(EventDatabaseEntity event);

    @Delete("DELETE FROM events WHERE id = #{id}")
    void deleteEvent(@Param("id") int id);
}
