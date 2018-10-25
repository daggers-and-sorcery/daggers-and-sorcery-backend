package com.morethanheroic.swords.tavern.repository.domain;

import com.morethanheroic.swords.tavern.repository.dao.ChatDatabaseEntry;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatEntryRepository {

    @Select("SELECT * FROM chat ORDER BY writing_time DESC LIMIT 25")
    List<ChatDatabaseEntry> getLastTwentyFiveMessage();

    @Insert("INSERT INTO chat SET user_id = #{userId}, message = #{message}, writing_time = NOW()")
    void saveEntry(ChatDatabaseEntry chatDatabaseEntry);
}
