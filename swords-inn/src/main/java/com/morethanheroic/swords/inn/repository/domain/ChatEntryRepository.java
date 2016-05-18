package com.morethanheroic.swords.inn.repository.domain;

import com.morethanheroic.swords.inn.repository.dao.ChatDatabaseEntry;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatEntryRepository {

    @Select("SELECT * FROM chat ORDER BY writing_time DESC LIMIT 25")
    List<ChatDatabaseEntry> getLastTwentyFiveMessage();

    @Insert("INSERT INTO chat SET user_id = #{userId}, message = #{message}, writing_time = NOW()")
    void saveEntry(ChatDatabaseEntry chatDatabaseEntry);
}
